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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RankStatService {
    private static final int CURRENT_SEASON = -1;
    private static final String[] REGIONS = {"apac", "emea", "ncsa"};

    private final RankStatRepository rankRepository;
    private final PlayerRepository playerRepository;
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
        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity(platform, id);
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
        List<RankStat> playerRankList = player.getRankList();
        for(int season = 1; season <= currentSeason; season++) {
            RankStatDto rankstatDto = ubiApi.getRankStat(platform, id, region, season);
            if(rankstatDto.getMaxMmr() == 0 && season != currentSeason) {
                continue; // 현재 시즌은 저장하지만, 이전 시즌이 플레이를 안한 경우는 무시
            }

            RankStat rankStat = new RankStat(rankstatDto, player);
            rankRepository.save(rankStat);
            player.getRankList().add(rankStat);
        }
    }
}
