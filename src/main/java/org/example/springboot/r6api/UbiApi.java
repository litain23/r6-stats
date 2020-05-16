package org.example.springboot.r6api;

import com.google.gson.*;
import lombok.RequiredArgsConstructor;
import org.example.springboot.exception.r6api.R6NotFoundPlayerProfileException;
import org.example.springboot.r6api.dto.CasualPvpDto;
import org.example.springboot.r6api.dto.GeneralPvpDto;
import org.example.springboot.r6api.dto.OperatorDto;
import org.example.springboot.r6api.dto.RankPvpDto;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.springboot.r6api.RequestParam.RequestType;
import static org.example.springboot.r6api.RequestParam.RequestType.*;

@RequiredArgsConstructor
@Component
public class UbiApi {
    private static final String RANK_URL_TEMPLATE = "https://public-ubiservices.ubi.com/v1/spaces/%s/sandboxes/%s/r6karma/players?board_id=pvp_ranked&profile_ids=%s&region_id=%s&season_id=%s";
    private static final String PROFILE_URL_TEMPLATE = "https://public-ubiservices.ubi.com/v2/profiles?platformType=%s&nameOnPlatform=%s";
    private static final String GENERAL_URL_TEMPLATE = "https://public-ubiservices.ubi.com/v1/spaces/%s/sandboxes/%s/playerstats2/statistics?populations=%s&statistics=%s";

    private final UbiAuthApi ubiAuthApi;

    public List<OperatorDto> getOperatorsStat(String platform, String id) {
        Profile findProfile = getProfile(platform, id);
        String operatorsUrl = makeGeneralUrl(OPERATOR, platform, findProfile.getUserId());

        String response= getDataUsingApi(operatorsUrl);
        return parseResponseToOperatorList(response, findProfile.getUserId());
    }

    public GeneralPvpDto getGeneralPvp(String platform, String id) {
        Profile findProfile = getProfile(platform, id);
        String generalPvpUrl = makeGeneralUrl(GENERAL_PVP, platform, findProfile.getUserId());

        String response = getDataUsingApi(generalPvpUrl);
        return parseResponseToGeneralPvpDto(response, findProfile.getUserId());
    }

    public CasualPvpDto getCasualPvp(String platform, String id) {
        Profile findProfile = getProfile(platform, id);
        String casualPvpUrl = makeGeneralUrl(CASUAL_PVP, platform, findProfile.getUserId());

        String response = getDataUsingApi(casualPvpUrl);
        return parseResponseToCasualPvpDto(response, findProfile.getUserId());
    }

    public RankPvpDto getRankPvp(String platform, String id) {
        Profile findProfile = getProfile(platform, id);
        String rankPvpUrl = makeGeneralUrl(RANK_PVP, platform, findProfile.getUserId());

        String response = getDataUsingApi(rankPvpUrl);
        return parseResponseToRankPvpDto(response, findProfile.getUserId());
    }

    public String getRankStat(String platform, String id, int season) {
        Profile findIdProfile = getProfile(platform, id);

        String region = "apac";
        String rankUrl = String.format(RANK_URL_TEMPLATE,
                Platform.platformToSpaceId(platform),
                Platform.platformToPlatformId(platform),
                findIdProfile.getUserId(),
                region,
                season
        );

        String responseRankData = getDataUsingApi(rankUrl);
        Gson gson = new GsonBuilder()
                .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        JsonObject rankStat = gson.fromJson(responseRankData, JsonObject.class);
        rankStat = rankStat.get("players").getAsJsonObject().get(findIdProfile.getUserId()).getAsJsonObject();

        //season 13 까지 max_mmr 만 Double 로 들어옴 (ex. 3145.312)
        Double maxMmr = rankStat.get("max_mmr").getAsDouble();
        int intMaxMmr = maxMmr.intValue();
        rankStat.addProperty("max_mmr", intMaxMmr);

        // deaths => death 로 이름 변경 (death 로 통일)
        int death = rankStat.get("deaths").getAsInt();
        rankStat.addProperty("death", death);
        return rankStat.toString();
    }

    private String makeGeneralUrl(RequestType requestType, String platform, String userId) {
        String requestParam = String.join(",", RequestParam.map.get(requestType));
        return String.format(GENERAL_URL_TEMPLATE,
                Platform.platformToSpaceId(platform),
                Platform.platformToPlatformId(platform),
                userId,
                requestParam
        );
    }

    private String parseUserId(String response, String userId) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        jsonObject = jsonObject.getAsJsonObject("results").getAsJsonObject(userId);
        if(jsonObject == null) {
            throw new R6NotFoundPlayerProfileException("Not found player id or platform");
        }
        String parsedStr = jsonObject.get(userId).toString();
        return parsedStr;
    }

    private Profile getProfile(String platform, String id) {
        try {
            String profileUrl = String.format(PROFILE_URL_TEMPLATE, platform, id);
            String responseProfile = getDataUsingApi(profileUrl);
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseProfile, JsonObject.class);
            JsonArray jsonArray =jsonObject.get("profiles").getAsJsonArray();
            return gson.fromJson(jsonArray.get(0), Profile.class);
        } catch (IndexOutOfBoundsException e) {
            throw new R6NotFoundPlayerProfileException("Not found player id or platform");
        }
    }

    private String getDataUsingApi(String requestUrl) {
        AuthToken token = ubiAuthApi.getAuthToken();
        try {
            URL url = new URL(requestUrl);

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Ubi-SessionId", token.getSessionId());
            conn.setRequestProperty("Authorization", "Ubi_v1 t=" + token.getTicket());
            conn.setRequestProperty("Ubi-AppId", UbiAuthApi.UPP_APP_ID);

            int responseCode = conn.getResponseCode();
            if(responseCode == HttpsURLConnection.HTTP_OK || responseCode == HttpsURLConnection.HTTP_ACCEPTED) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                return br.readLine();
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    // TODO : PVP 에 관한 get()Pvp 함수가 전반적으로 중복됨, 파서 또한 중복되어 존재
    private GeneralPvpDto parseResponseToGeneralPvpDto(String response, String userId) {
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

    private RankPvpDto parseResponseToRankPvpDto(String response, String userId) {
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

    private CasualPvpDto parseResponseToCasualPvpDto(String response, String userId) {
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

    private List<OperatorDto> parseResponseToOperatorList(String response, String userId) {
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
}
