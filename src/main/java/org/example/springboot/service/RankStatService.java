package org.example.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.rankstat.RankStat;
import org.example.springboot.domain.rankstat.RankStatRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.dto.RankStatDto;
import org.example.springboot.web.dto.RankStatRegionResponseDto;
import org.example.springboot.web.dto.RankStatResponseDto;
import org.example.springboot.web.dto.RankStatSeasonResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RankStatService {
    private static final int CURRENT_SEASON = -1;
    private static final String[] PAST_SEASONS_NAME = {"apac", "emea", "ncsa"};
    private static final String CURRENT_SEASON_NAME = "global";

    private final RankStatRepository rankRepository;
    private final PlayerService playerService;
    private final UbiApi ubiApi;

    public List<RankStatRegionResponseDto> getRankStat(String platform, String id, int season) {
        List<RankStatDto> rankDtoList = getRankStatUsingR6API(platform, id, season);
        return convertStatDtoToRegionDto(rankDtoList.stream().map(RankStatResponseDto::new).collect(Collectors.toList()));
    }

    public List<RankStatSeasonResponseDto> getRankStatAllSeason(String platform, String id) {
        Player player = playerService.findPlayerIfNotExistReturnNewEntity(platform, id);
        RankStatDto currentRankStatDto = ubiApi.getRankStat(platform, id, CURRENT_SEASON_NAME, CURRENT_SEASON);
        RankStat currentRankStat = new RankStat(currentRankStatDto, player);

        if(player.getRankList().isEmpty()) {
            savePreviousSeasons(platform, id, currentRankStatDto.getSeason(), player);
        }
        RankStat lastRankStat = player.getRankList().stream().max(Comparator.comparing(RankStat::getSeason)).orElse(currentRankStat);
        if(lastRankStat.getSeason() == currentRankStatDto.getSeason()){
            lastRankStat.updateRankStat(currentRankStatDto);
        } else if(lastRankStat.getSeason() < currentRankStatDto.getSeason()) {
            rankRepository.save(currentRankStat);
            player.getRankList().add(currentRankStat);
        }


        return convertStatDtoToSeasonDto(
                player.getRankList().stream()
                        .map(RankStatResponseDto::new)
                        .filter(dto -> dto.getMaxMmr() > 0)
                        .collect(Collectors.toList())
        );
    }

    private List<RankStatSeasonResponseDto> convertStatDtoToSeasonDto(List<RankStatResponseDto> dto) {
        List<RankStatSeasonResponseDto> ret = new ArrayList<>();
        Map<Integer, List<RankStatResponseDto>> map = dto.stream().collect(Collectors.groupingBy(RankStatResponseDto::getSeason));

        for(int season : map.keySet()) {
            ret.add(new RankStatSeasonResponseDto(season, convertStatDtoToRegionDto(map.get(season))));
        }
        ret.sort(Comparator.comparing(t -> t.getSeason(), Comparator.reverseOrder()));
        return ret;
    }

    private List<RankStatRegionResponseDto> convertStatDtoToRegionDto(List<RankStatResponseDto> dto) {
        List<RankStatRegionResponseDto> ret = new ArrayList<>();
        Map<String, List<RankStatResponseDto>> map = dto.stream().collect(Collectors.groupingBy(RankStatResponseDto::getRegion));

        for(String region : map.keySet()) {
            ret.add(new RankStatRegionResponseDto(region, map.get(region).get(0)));
        }

        return ret;
    }

    @Transactional
    void savePreviousSeasons(String platform, String id, int currentSeason, Player player) {
        List<CompletableFuture<RankStatDto>> completableFutureList = new ArrayList<>();
        for(int season = 1; season <= 17; season++) {
            int finalSeason = season;
            for(String region : PAST_SEASONS_NAME) {
                completableFutureList.add(CompletableFuture.supplyAsync(() -> {
                    return ubiApi.getRankStat(platform, id, region, finalSeason);
                }));
            }
        }

        for(int season = 18; season <= currentSeason; season++) {
            int finalSeason = season;
            completableFutureList.add(CompletableFuture.supplyAsync(() -> {
                return ubiApi.getRankStat(platform, id, CURRENT_SEASON_NAME, finalSeason);
            }));
        }

        CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[completableFutureList.size()])).join();
        List<RankStatDto> dtoList = completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList());

        for(RankStatDto dto : dtoList) {
            if(dto.getMaxMmr() == 0 && dto.getSeason() != currentSeason) {
                continue;
            }
            RankStat rankStat = new RankStat(dto, player);
            rankRepository.save(rankStat);
            player.getRankList().add(rankStat);
        }
    }

    private List<RankStatDto> getRankStatUsingR6API(String platform, String id, int season) {
        List<RankStatDto> ret = new ArrayList<>();
        if(season == -1 || season >= 18) {
            ret.add(ubiApi.getRankStat(platform, id, CURRENT_SEASON_NAME, season));
        } else {
            for(String region : PAST_SEASONS_NAME) {
                ret.add(ubiApi.getRankStat(platform, id, region, season));
            }
        }
        return ret;
    }
}
