package org.example.springboot.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.casualpvp.CasualPvp;
import org.example.springboot.domain.casualpvp.CasualPvpRepository;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.web.dto.CasualPvpResponseDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class CasualPvpService {
    private final CasualPvpRepository casualPvpRepository;
    private final PlayerRepository playerRepository;
    private final UbiApi ubiApi;

    public CasualPvpResponseDto getCasualPvp(String platform, String id) {
        return getCasualPvp(platform, id, false);
    }

    public CasualPvpResponseDto getCasualPvp(String platform, String id, boolean isSave) {
        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity(platform, id);
        CasualPvp casualPvp = parseResponseStr(ubiApi.getCasualPvp(platform, id));

        if(isSave) {
            casualPvp.setPlayer(player);
            casualPvpRepository.save(casualPvp);
            player.getCasualPvpList().add(casualPvp);
        }
        return new CasualPvpResponseDto(casualPvp);
    }

    private CasualPvp parseResponseStr(String generalPvpStr) {
        Map<String, Double> generalPvpMap = new Gson().fromJson(generalPvpStr, Map.class);

        int matchLost = generalPvpMap.getOrDefault("casualpvp_matchlost:infinite", 0.0).intValue();
        int matchWon = generalPvpMap.getOrDefault("casualpvp_matchwon:infinite", 0.0).intValue();;
        int matchPlayed = generalPvpMap.getOrDefault("casualpvp_matchplayed:infinite", 0.0).intValue();;
        int kills = generalPvpMap.getOrDefault("casualpvp_kills:infinite", 0.0).intValue();
        int death = generalPvpMap.getOrDefault("casualpvp_death:infinite", 0.0).intValue();
        int timePlayed = generalPvpMap.getOrDefault("casualpvp_timeplayed:infinite", 0.0).intValue();;

        CasualPvp casualPvp = CasualPvp.builder()
                .death(death)
                .kills(kills)
                .timePlayed(timePlayed)
                .matchLost(matchLost)
                .matchWon(matchWon)
                .matchPlayed(matchPlayed)
                .build();
        return casualPvp;
    }

}
