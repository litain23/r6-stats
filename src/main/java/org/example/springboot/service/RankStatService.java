package org.example.springboot.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.domain.rankstat.RankStat;
import org.example.springboot.domain.rankstat.RankStatRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.web.dto.RankStatResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RankStatService {
    private final RankStatRepository rankRepository;
    private final PlayerRepository playerRepository;
    private final UbiApi ubiApi;

    @Transactional
    public RankStatResponseDto getRankStat(String platform, String id, int season) {
        RankStat currentRankStat = parseResponseStr(ubiApi.getRankStat(platform, id, season));
        int currentSeason = currentRankStat.getSeason();

        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity(platform, id);

        List<RankStat> playerRankList = player.getRankList();
        if(playerRankList.isEmpty()) {
            for(int ss = 1; ss <= currentSeason; ss++) {
                RankStat rankstat = parseResponseStr(ubiApi.getRankStat(platform, id, ss));
                rankRepository.save(rankstat);
                playerRankList.add(rankstat);
            }
        } else {
            RankStat lastRankStat = playerRankList.get(playerRankList.size() - 1);
            // 플레이어의 마지막 랭크 시즌이 현재 시즌과 같으면 업데이트, 아니면 Add
            if(lastRankStat.getSeason() == currentSeason) {
                lastRankStat.updateRankStat(currentRankStat);
            } else {
                rankRepository.save(currentRankStat);
                playerRankList.add(currentRankStat);
            }
        }

        return new RankStatResponseDto(currentRankStat);
    }

    @Transactional
    public List<RankStatResponseDto> getRankStatAllSeason(String platform, String id) {
        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity(platform, id);

        RankStat currentRankStat = parseResponseStr(ubiApi.getRankStat(platform, id, -1));
        int currentSeason = currentRankStat.getSeason();

        List<RankStat> playerRankList = player.getRankList();
        if(playerRankList.isEmpty()) {
            for(int season = 1; season <= currentSeason; season++) {
                RankStat rankstat = parseResponseStr(ubiApi.getRankStat(platform, id, season));
                rankRepository.save(rankstat);
                playerRankList.add(rankstat);
            }
        } else {
            RankStat lastRankStat = playerRankList.get(playerRankList.size() - 1);
            // 플레이어의 마지막 랭크 시즌이 현재 시즌과 같으면 업데이트, 아니면 Add
            if(lastRankStat.getSeason() == currentSeason) {
                lastRankStat.updateRankStat(currentRankStat);
            } else {
                rankRepository.save(currentRankStat);
                playerRankList.add(currentRankStat);
            }
        }

        return playerRankList.stream()
                    .map(RankStatResponseDto::new)
                    .collect(Collectors.toList());
    }

    private RankStat parseResponseStr(String strJson) {
        Gson gson = new GsonBuilder()
                .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        return gson.fromJson(strJson, RankStat.class);
    }
}
