package org.example.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.domain.rankstat.RankStat;
import org.example.springboot.domain.rankstat.RankStatRepository;
import org.example.springboot.exception.r6api.R6ErrorException;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.dto.RankStatDto;
import org.example.springboot.web.dto.RankStatRegionResponseDto;
import org.example.springboot.web.dto.RankStatRequestDto;
import org.example.springboot.web.dto.RankStatResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RankStatService {
    private static final int CURRENT_SEASON = -1;
    private static final String[] REGIONS = {"apac", "emea", "ncsa"};

    private final RankStatRepository rankRepository;
    private final PlayerService playerService;
    private final UbiApi ubiApi;


    @Transactional
    public List<RankStatRegionResponseDto> getRankStat(String platform, String id, int season) {

        List<RankStatDto> rankDtoList = new ArrayList<>();
        for(String region : REGIONS) {
            rankDtoList.add(ubiApi.getRankStat(platform, id, region, season));
        }
        return convertStatDtoToRegionDto(rankDtoList.stream().map(RankStatResponseDto::new).collect(Collectors.toList()));
    }

    @Transactional
    public List<RankStatRegionResponseDto> getRankStatAllSeason(String platform, String id) {
        Player player = playerService.findPlayerIfNotExistReturnNewEntity(platform, id);
        int currentSeason = ubiApi.getRankStat(platform, id, REGIONS[0], CURRENT_SEASON).getSeason();

        for(String region : REGIONS) {
            Optional<List<RankStat>> rankStatList = rankRepository.findByRegionAndPlayer(region, player);

            if(!rankStatList.isPresent()) {
                savePreviousSeasons(platform, id, region, currentSeason, player);
            } else {
                RankStat lastRankStat = rankStatList.get()
                            .stream()
                            .max(Comparator.comparing(RankStat::getSeason))
                            .orElseThrow(RuntimeException::new);
                RankStatDto currentRankStatDto = ubiApi.getRankStat(platform, id, region, currentSeason);

                if(lastRankStat.getSeason() == currentRankStatDto.getSeason()) {
                    lastRankStat.updateRankStat(currentRankStatDto);
                } else {
                    if(lastRankStat.getMaxMmr() == 0) {
                        rankRepository.delete(lastRankStat);
                    }
                    RankStat currentRankStat = new RankStat(currentRankStatDto, player);
                    rankRepository.save(currentRankStat);
                    player.getRankList().add(currentRankStat);
                }
            }
        }
        return convertStatDtoToRegionDto(player.getRankList().stream().map(RankStatResponseDto::new).collect(Collectors.toList()));
    }

    private List<RankStatRegionResponseDto> convertStatDtoToRegionDto(List<RankStatResponseDto> dto) {
        List<RankStatRegionResponseDto> ret = new ArrayList<>();
        Map<String, List<RankStatResponseDto>> map = dto.stream().collect(Collectors.groupingBy(RankStatResponseDto::getRegion));

        for(String region : map.keySet()) {
            ret.add(new RankStatRegionResponseDto(region, map.get(region)));
        }

        return ret;
    }

    private void savePreviousSeasons(String platform, String id, String region, int currentSeason, Player player) {
        List<CompletableFuture<RankStatDto>> completableFutureList = new ArrayList<>();
        for(int season = 1; season <= currentSeason; season++) {
            int finalSeason = season;
            completableFutureList.add(CompletableFuture.supplyAsync(() -> {
                return ubiApi.getRankStat(platform, id, region, finalSeason);
            }));
        }
        CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[completableFutureList.size()])).join();
        List<RankStatDto> dtoList = completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList());

        for(RankStatDto dto : dtoList) {
            if(dto.getMaxMmr() == 0 && dto.getSeason() != currentSeason) {
                // 현재 시즌에 플레이 하지 않더라도 DB 에 저장해놔서, 이 메소드가 검색할 때 마다 호출되는 것을 막음
                continue;
            }
            RankStat rankStat = new RankStat(dto, player);
            rankRepository.save(rankStat);
            player.getRankList().add(rankStat);
        }
    }
}
