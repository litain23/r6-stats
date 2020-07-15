package me.r6_search.r6api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import me.r6_search.exception.r6api.R6NotFoundPlayerProfileException;
import me.r6_search.r6api.dto.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UbiApiParser {
    private String parseUserId(String response, String userId) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        jsonObject = jsonObject.getAsJsonObject("results").getAsJsonObject(userId);
        if(jsonObject == null) {
            throw new R6NotFoundPlayerProfileException("Not found player id or platform");
        }
        return jsonObject.toString();
    }

    protected GeneralPvpDto parseResponseToGeneralPvpDto(String response, String userId) {
        response = parseUserId(response, userId);
        Map<String, Double> generalPvpMap = new Gson().fromJson(response, Map.class);

        int matchLost = generalPvpMap.getOrDefault("generalpvp_matchlost:infinite", 0.0).intValue();
        int matchWon = generalPvpMap.getOrDefault("generalpvp_matchwon:infinite", 0.0).intValue();;
        int matchPlayed = generalPvpMap.getOrDefault("generalpvp_matchplayed:infinite", 0.0).intValue();;
        int kills = generalPvpMap.getOrDefault("generalpvp_kills:infinite", 0.0).intValue();
        int death = generalPvpMap.getOrDefault("generalpvp_death:infinite", 0.0).intValue();
        int penetrationKills = generalPvpMap.getOrDefault("generalpvp_penetrationkills:infinite", 0.0).intValue();;
        int meleeKills = generalPvpMap.getOrDefault("generalpvp_meleekills:infinite", 0.0).intValue();
        int killAssists = generalPvpMap.getOrDefault("generalpvp_killassists:infinite", 0.0).intValue();;
        int headShot = generalPvpMap.getOrDefault("generalpvp_headshot:infinite", 0.0).intValue();;
        int revive = generalPvpMap.getOrDefault("generalpvp_revive:infinite", 0.0).intValue();
        int bulletHit = generalPvpMap.getOrDefault("generalpvp_bullethit:infinite", 0.0).intValue();
        int timePlayed = generalPvpMap.getOrDefault("generalpvp_timeplayed:infinite", 0.0).intValue();;

        GeneralPvpDto generalPvp = GeneralPvpDto.builder()
                .matchLost(matchLost)
                .matchWon(matchWon)
                .matchPlayed(matchPlayed)
                .kills(kills)
                .death(death)
                .penetrationKills(penetrationKills)
                .meleeKills(meleeKills)
                .killAssists(killAssists)
                .headShot(headShot)
                .revive(revive)
                .bulletHit(bulletHit)
                .timePlayed(timePlayed)
                .build();

        return generalPvp;
    }

    protected RankPvpDto parseResponseToRankPvpDto(String response, String userId) {
        response = parseUserId(response, userId);
        Map<String, Double> generalPvpMap = new Gson().fromJson(response, Map.class);

        int matchLost = generalPvpMap.getOrDefault("rankedpvp_matchlost:infinite", 0.0).intValue();
        int matchWon = generalPvpMap.getOrDefault("rankedpvp_matchwon:infinite", 0.0).intValue();
        int matchPlayed = generalPvpMap.getOrDefault("rankedpvp_matchplayed:infinite", 0.0).intValue();
        int kills = generalPvpMap.getOrDefault("rankedpvp_kills:infinite", 0.0).intValue();
        int death = generalPvpMap.getOrDefault("rankedpvp_death:infinite", 0.0).intValue();
        int timePlayed = generalPvpMap.getOrDefault("rankedpvp_timeplayed:infinite", 0.0).intValue();

        RankPvpDto rankPvp = RankPvpDto.builder()
                .death(death)
                .kills(kills)
                .timePlayed(timePlayed)
                .matchLost(matchLost)
                .matchWon(matchWon)
                .matchPlayed(matchPlayed)
                .build();
        return rankPvp;
    }

    protected CasualPvpDto parseResponseToCasualPvpDto(String response, String userId) {
        response = parseUserId(response, userId);
        Map<String, Double> generalPvpMap = new Gson().fromJson(response, Map.class);

        int matchLost = generalPvpMap.getOrDefault("casualpvp_matchlost:infinite", 0.0).intValue();
        int matchWon = generalPvpMap.getOrDefault("casualpvp_matchwon:infinite", 0.0).intValue();;
        int matchPlayed = generalPvpMap.getOrDefault("casualpvp_matchplayed:infinite", 0.0).intValue();;
        int kills = generalPvpMap.getOrDefault("casualpvp_kills:infinite", 0.0).intValue();
        int death = generalPvpMap.getOrDefault("casualpvp_death:infinite", 0.0).intValue();
        int timePlayed = generalPvpMap.getOrDefault("casualpvp_timeplayed:infinite", 0.0).intValue();;

        CasualPvpDto casualPvp = CasualPvpDto.builder()
                .death(death)
                .kills(kills)
                .timePlayed(timePlayed)
                .matchLost(matchLost)
                .matchWon(matchWon)
                .matchPlayed(matchPlayed)
                .build();

        return casualPvp;
    }

    protected List<OperatorDto> parseResponseToOperatorList(String response, String userId) {
        response = parseUserId(response, userId);
        Map<String, Double> operatorMap = new Gson().fromJson(response, Map.class);
        Map<String, Map<String, Double>> groupByIndexMap = operatorMap.keySet()
                .stream()
                .collect(Collectors.groupingBy(key -> {
                    String[] splitKey = key.split(":");
                    String index = splitKey[1] + ":" + splitKey[2];
                    return index;
                }, Collectors.toMap(key -> {
                    String prefix = "operatorpvp_";
                    String changeKey = key.substring(prefix.length());
                    return changeKey.split(":")[0];
                }, key -> operatorMap.get(key))));


        List<OperatorDto> operatorDtoList = new ArrayList<>();

        List<OperatorIndex.OperatorInfo> operatorIndexList = OperatorIndex.indexList;
        for(OperatorIndex.OperatorInfo operatorIndex : operatorIndexList) {
            String index = operatorIndex.getIndex();
            Map<String, Double> operatorStat = groupByIndexMap.getOrDefault(index, Collections.EMPTY_MAP);

            OperatorDto op = OperatorDto.builder()
                    .death(operatorStat.getOrDefault("death", 0.0).intValue())
                    .roundLost(operatorStat.getOrDefault("roundlost", 0.0).intValue())
                    .roundWon(operatorStat.getOrDefault("roundwon", 0.0).intValue())
                    .timePlayed(operatorStat.getOrDefault("timeplayed", 0.0).intValue())
                    .totalXp(operatorStat.getOrDefault("totalxp", 0.0).intValue())
                    .meleeKills(operatorStat.getOrDefault("meleekills", 0.0).intValue())
                    .headShot(operatorStat.getOrDefault("headshot", 0.0).intValue())
                    .kills(operatorStat.getOrDefault("kills", 0.0).intValue())
                    .operatorIndex(index)
                    .category(operatorIndex.getCategory())
                    .name(operatorIndex.getName())
                    .build();

            operatorDtoList.add(op);
        }
        return operatorDtoList;
    }

    protected RankStatDto parseResponseToRankStatDto(String response, String userId) {
        Gson gson = new GsonBuilder()
                .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        JsonObject rankStat = gson.fromJson(response, JsonObject.class);
        rankStat = rankStat.getAsJsonObject("players").getAsJsonObject(userId);

        //season 13 까지 max_mmr 만 Double 로 들어옴 (ex. 3145.312)
        Double maxMmr = rankStat.get("max_mmr").getAsDouble();
        int intMaxMmr = maxMmr.intValue();
        rankStat.addProperty("max_mmr", intMaxMmr);

        // deaths => death 로 이름 변경 (death 로 통일)
        int death = rankStat.get("deaths").getAsInt();
        rankStat.addProperty("death", death);

        RankStatDto dto = gson.fromJson(rankStat, RankStatDto.class);
        dto.setCreatedTime(LocalDateTime.now());
        SeasonMmrStringConverter converter = new SeasonMmrStringConverter();
        if(dto.getMaxMmr()== 0) {
            dto.setRankString("UNRANKED");
            dto.setMaxRankString("UNRANKED");
        } else {
            dto.setMaxRankString(converter.convertMmrToStringRank(dto.getSeason(), dto.getMaxMmr()));
            dto.setRankString(converter.convertMmrToStringRank(dto.getSeason(), dto.getMmr()));
        }
        if(UbiApi.currentSeason < dto.getSeason()) {
            UbiApi.currentSeason = dto.getSeason();
        }
        return dto;
    }
}
