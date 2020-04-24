package org.example.springboot.service.operators;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.generalpvp.GeneralPvp;
import org.example.springboot.domain.generalpvp.GeneralPvpRepository;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.domain.player.PlayerRepositoryBasic;
import org.example.springboot.r6api.API;
import org.example.springboot.r6api.AuthToken;
import org.example.springboot.r6api.UbiAuthApi;
import org.example.springboot.web.dto.GeneralPvpResponseDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class GeneralPvpService {
    private final GeneralPvpRepository generalPvpRepository;
    private final PlayerRepository playerRepository;

    public GeneralPvpResponseDto getGeneralPvp(String platform, String id) {
        AuthToken token = UbiAuthApi.getAuthToken();

        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity(platform, id);

        API api = new API(token);
        GeneralPvp generalPvp = parseResponseStr(api.getGeneralPvp(platform, id));
        generalPvpRepository.save(generalPvp);
        player.setGeneralPvp(generalPvp);

        return new GeneralPvpResponseDto(generalPvp);
    }

    private GeneralPvp parseResponseStr(String generalPvpStr) {
        Map<String, Double> generalPvpMap = new Gson().fromJson(generalPvpStr, Map.class);

        int totalMatchLost = generalPvpMap.getOrDefault("generalpvp_timeplayed:infinite", 0.0).intValue();
        int totalMatchWon = generalPvpMap.getOrDefault("generalpvp_matchwon:infinite", 0.0).intValue();;
        int totalMatchPlayed = generalPvpMap.getOrDefault("generalpvp_matchplayed:infinite", 0.0).intValue();;
        int totalKills = generalPvpMap.getOrDefault("generalpvp_kills:infinite", 0.0).intValue();
        int totalDeath = generalPvpMap.getOrDefault("generalpvp_death:infinite", 0.0).intValue();
        int totalPenetrationKills = generalPvpMap.getOrDefault("generalpvp_penetrationkills:infinite", 0.0).intValue();;
        int totalMeleeKills = generalPvpMap.getOrDefault("generalpvp_meleekills:infinite", 0.0).intValue();
        int totalKillAssists = generalPvpMap.getOrDefault("generalpvp_killassists:infinite", 0.0).intValue();;
        int totalHeadShot = generalPvpMap.getOrDefault("generalpvp_headshot:infinite", 0.0).intValue();;
        int totalRevive = generalPvpMap.getOrDefault("generalpvp_revive:infinite", 0.0).intValue();
        int totalBulletHit = generalPvpMap.getOrDefault("generalpvp_bullethit:infinite", 0.0).intValue();
        int totalTimePlayed = generalPvpMap.getOrDefault("generalpvp_timeplayed:infinite", 0.0).intValue();;

        GeneralPvp generalPvp = GeneralPvp.builder()
                .totalMatchLost(totalMatchLost)
                .totalMatchWon(totalMatchWon)
                .totalMatchPlayed(totalMatchPlayed)
                .totalKills(totalKills)
                .totalDeath(totalDeath)
                .totalPenetrationKills(totalPenetrationKills)
                .totalMeleeKills(totalMeleeKills)
                .totalKillAssists(totalKillAssists)
                .totalHeadShot(totalHeadShot)
                .totalRevive(totalRevive)
                .totalBulletHit(totalBulletHit)
                .totalTimePlayed(totalTimePlayed)
                .build();

        return generalPvp;

    }

}
