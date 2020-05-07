package org.example.springboot.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.rankpvp.RankPvp;
import org.example.springboot.domain.rankpvp.RankPvpRepository;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.web.dto.RankPvpResponseDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class RankPvpService {
    private final RankPvpRepository rankPvpRepository;
    private final PlayerRepository playerRepository;
    private final UbiApi ubiApi;

    public RankPvpResponseDto getRankPvp(String platform, String id) {
        return getRankPvp(platform, id, false);
    }

    public RankPvpResponseDto getRankPvp(String platform, String id, boolean isSave) {
        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity(platform, id);
        RankPvp rankPvp = parseResponseStr(ubiApi.getRankPvp(platform, id));

        if(isSave) {
            rankPvp.setPlayer(player);
            rankPvpRepository.save(rankPvp);
            player.getRankPvpList().add(rankPvp);
        }

        return new RankPvpResponseDto(rankPvp);
    }

    private RankPvp parseResponseStr(String generalPvpStr) {
        Map<String, Double> generalPvpMap = new Gson().fromJson(generalPvpStr, Map.class);

        int matchLost = generalPvpMap.getOrDefault("rankpvp_matchlost:infinite", 0.0).intValue();
        int matchWon = generalPvpMap.getOrDefault("rankpvp_matchwon:infinite", 0.0).intValue();
        int matchPlayed = generalPvpMap.getOrDefault("rankpvp_matchplayed:infinite", 0.0).intValue();
        int kills = generalPvpMap.getOrDefault("rankpvp_kills:infinite", 0.0).intValue();
        int death = generalPvpMap.getOrDefault("rankpvp_death:infinite", 0.0).intValue();
        int timePlayed = generalPvpMap.getOrDefault("rankpvp_timeplayed:infinite", 0.0).intValue();

        RankPvp rankPvp = RankPvp.builder()
                .death(death)
                .kills(kills)
                .timePlayed(timePlayed)
                .matchLost(matchLost)
                .matchWon(matchWon)
                .matchPlayed(matchPlayed)
                .build();
        return rankPvp;
    }
}
