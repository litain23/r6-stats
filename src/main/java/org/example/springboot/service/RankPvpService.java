package org.example.springboot.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.domain.rankpvp.RankPvp;
import org.example.springboot.domain.rankpvp.RankPvpRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.web.dto.RankPvpResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RankPvpService {
    private final RankPvpRepository rankPvpRepository;
    private final PlayerRepository playerRepository;
    private final UbiApi ubiApi;

    @Transactional
    public RankPvpResponseDto getRankPvp(String platform, String id) {
        return getRankPvp(platform, id, false);
    }

    @Transactional
    public RankPvpResponseDto getRankPvp(String platform, String id, boolean isSave) {
        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity(platform, id);
        RankPvp rankPvp = parseResponseStr(ubiApi.getRankPvp(platform, id));

        System.out.println(rankPvp);


        if(isSave) {
            rankPvp.setPlayer(player);
            rankPvpRepository.save(rankPvp);
            player.getRankPvpList().add(rankPvp);
        }

        return new RankPvpResponseDto(rankPvp);
    }

    @Transactional
    public List<RankPvpResponseDto> getRankPvpAll(String platform, String id) {
        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity(platform, id);
        List<RankPvpResponseDto> ret = player.getRankPvpList().stream()
                .map(RankPvpResponseDto::new)
                .sorted(Comparator.comparing(RankPvpResponseDto::getCreatedTime).reversed())
                .collect(Collectors.toList());

        RankPvpResponseDto recentPvp = getRankPvp(platform, id);
        recentPvp.setCreatedTime(LocalDateTime.now());
        ret.add(0, recentPvp);
        return ret;
    }

    private RankPvp parseResponseStr(String generalPvpStr) {
        Map<String, Double> generalPvpMap = new Gson().fromJson(generalPvpStr, Map.class);

        int matchLost = generalPvpMap.getOrDefault("rankedpvp_matchlost:infinite", 0.0).intValue();
        int matchWon = generalPvpMap.getOrDefault("rankedpvp_matchwon:infinite", 0.0).intValue();
        int matchPlayed = generalPvpMap.getOrDefault("rankedpvp_matchplayed:infinite", 0.0).intValue();
        int kills = generalPvpMap.getOrDefault("rankedpvp_kills:infinite", 0.0).intValue();
        int death = generalPvpMap.getOrDefault("rankedpvp_death:infinite", 0.0).intValue();
        int timePlayed = generalPvpMap.getOrDefault("rankedpvp_timeplayed:infinite", 0.0).intValue();

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
