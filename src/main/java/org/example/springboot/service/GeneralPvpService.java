package org.example.springboot.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.web.dto.GeneralPvpResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class GeneralPvpService {
    private final UbiApi ubiApi;

    @Transactional
    public GeneralPvpResponseDto getGeneralPvp(String platform, String id) {
        return parseResponseStr(ubiApi.getGeneralPvp(platform, id));
    }

    private GeneralPvpResponseDto parseResponseStr(String generalPvpStr) {
        Map<String, Double> generalPvpMap = new Gson().fromJson(generalPvpStr, Map.class);

        int totalMatchLost = generalPvpMap.getOrDefault("generalpvp_matchlost:infinite", 0.0).intValue();
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

        GeneralPvpResponseDto generalPvp = GeneralPvpResponseDto.builder()
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
