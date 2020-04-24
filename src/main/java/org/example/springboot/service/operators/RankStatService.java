package org.example.springboot.service.operators;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.domain.rankstat.RankStat;
import org.example.springboot.domain.rankstat.RankStatRepository;
import org.example.springboot.domain.player.Player;
import org.example.springboot.r6api.API;
import org.example.springboot.r6api.AuthToken;
import org.example.springboot.r6api.UbiAuthApi;
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
    private AuthToken token;

    @Transactional
    public RankStatResponseDto updateCurrentSeasonRankStat(String platform, String id, int season) {
        token = UbiAuthApi.getAuthToken();
        API api = new API(token);
        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity(platform, id);

        List<RankStat> playerRankList = player.getRankList();

        RankStat rankStat = parseResponseStr(api.getRankStat(platform, id, season));
        int currentSeason = rankStat.getSeason();

        RankStat savedPlayerRankStat = playerRankList.stream()
                .filter(stat -> stat.getSeason() == currentSeason)
                .findFirst()
                .orElseGet(null);


        if(savedPlayerRankStat == null) {
            if(rankStat.getMaxMmr() == 0) {
                return null;
            } else {
                rankRepository.save(rankStat);
                player.getRankList().add(rankStat);
            }
        } else {
            savedPlayerRankStat.updateRankStat(rankStat);
        }

        return new RankStatResponseDto(rankStat);
    }

    @Transactional
    public List<RankStatResponseDto> getRankStatAllSeason(String platform, String id) {
        token = UbiAuthApi.getAuthToken();
        API api = new API(token);
        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity(platform, id);

        RankStat currentRankStat = parseResponseStr(api.getRankStat(platform, id, -1));
        int currentSeason = currentRankStat.getSeason();

        List<RankStat> playerRankList = player.getRankList();
        for(int season = 1; season < currentSeason; season++) {
            RankStat rankstat = parseResponseStr(api.getRankStat(platform, id, season));
            if(rankstat.getMaxMmr() == 0) {
                continue;
            }
            rankRepository.save(rankstat);
            playerRankList.add(rankstat);
        }
        rankRepository.save(currentRankStat);
        playerRankList.add(currentRankStat);

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
