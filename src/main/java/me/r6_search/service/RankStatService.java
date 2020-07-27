package me.r6_search.service;

import lombok.RequiredArgsConstructor;
import me.r6_search.r6api.UbiApi;
import me.r6_search.r6api.dto.RankStatDto;
import me.r6_search.model.player.Player;
import me.r6_search.model.rankstat.RankStat;
import me.r6_search.model.rankstat.RankStatRepository;
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

    public List<RankStatDto> getRankStat(Player player, int season) {
        return getRankStatUsingR6API(player.getPlatform(), player.getProfileId(), season);
    }

    @Transactional
    public List<RankStatDto> getRankStatAllSeason(Player player) {
        RankStatDto currentRankStatDto = ubiApi.getRankStat(player.getPlatform(), player.getProfileId(), CURRENT_SEASON_NAME, CURRENT_SEASON);
        RankStat currentRankStat = new RankStat(currentRankStatDto, player);

        if(player.getRankList().isEmpty()) {
            savePreviousSeasons(player, currentRankStatDto.getSeason());
        }
        RankStat lastRankStat = player.getRankList().stream().max(Comparator.comparing(RankStat::getSeason)).orElse(currentRankStat);
        if(lastRankStat.getSeason() == currentRankStatDto.getSeason()){
            lastRankStat.updateRankStat(currentRankStatDto);
        } else if(lastRankStat.getSeason() < currentRankStatDto.getSeason()) {
            rankRepository.save(currentRankStat);
            player.getRankList().add(currentRankStat);
        }
        return player.getRankList()
                .stream()
                .map(RankStatDto::new)
                .filter(dto -> dto.getMaxMmr() > 0)
                .collect(Collectors.toList());
    }


    private void savePreviousSeasons(Player player, int currentSeason) {
        List<CompletableFuture<RankStatDto>> completableFutureList = new ArrayList<>();
        for(int season = 1; season <= 17; season++) {
            int finalSeason = season;
            for(String region : PAST_SEASONS_NAME) {
                completableFutureList.add(CompletableFuture.supplyAsync(() -> {
                    return ubiApi.getRankStat(player.getPlatform(), player.getProfileId(), region, finalSeason);
                }));
            }
        }

        for(int season = 18; season <= currentSeason; season++) {
            int finalSeason = season;
            completableFutureList.add(CompletableFuture.supplyAsync(() -> {
                return ubiApi.getRankStat(player.getPlatform(), player.getProfileId(), CURRENT_SEASON_NAME, finalSeason);
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
