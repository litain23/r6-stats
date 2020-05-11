package org.example.springboot.r6api;

import com.google.gson.*;
import lombok.RequiredArgsConstructor;
import org.example.springboot.exception.r6api.R6NotFoundPlayerProfileException;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@RequiredArgsConstructor
@Component
public class UbiApi {
    private static final String RANK_URL_TEMPLATE = "https://public-ubiservices.ubi.com/v1/spaces/%s/sandboxes/%s/r6karma/players?board_id=pvp_ranked&profile_ids=%s&region_id=%s&season_id=%s";
    private static final String PROFILE_URL_TEMPLATE = "https://public-ubiservices.ubi.com/v2/profiles?platformType=%s&nameOnPlatform=%s";
    private static final String GENERAL_URL_TEMPLATE = "https://public-ubiservices.ubi.com/v1/spaces/%s/sandboxes/%s/playerstats2/statistics?populations=%s&statistics=%s";

    private final UbiAuthApi ubiAuthApi;

    public String getOperatorsStat(String platform, String id) {
        Profile findProfile = getProfile(platform, id);
        String operatorsUrl = String.format(GENERAL_URL_TEMPLATE,
                Platform.platformToSpaceId(platform),
                Platform.platformToPlatformId(platform),
                findProfile.getUserId(),
                String.join(",", RequestParam.OPERATORS)
        );

        String responseOperator = getDataUsingApi(operatorsUrl);
        return parseGeneralTemplateUrlResponse(responseOperator, findProfile.getUserId());
    }

    public String getGeneralPvp(String platform, String id) {
        Profile findProfile = getProfile(platform, id);
        String generalPvpUrl = String.format(GENERAL_URL_TEMPLATE,
                Platform.platformToSpaceId(platform),
                Platform.platformToPlatformId(platform),
                findProfile.getUserId(),
                String.join(",", RequestParam.GENERAL_PVP)
        );

        String responseGeneralPvp = getDataUsingApi(generalPvpUrl);
        return parseGeneralTemplateUrlResponse(responseGeneralPvp, findProfile.getUserId());
    }

    public String getCasualPvp(String platform, String id) {
        Profile findProfile = getProfile(platform, id);
        String casualPvpUrl = String.format(GENERAL_URL_TEMPLATE,
                Platform.platformToSpaceId(platform),
                Platform.platformToPlatformId(platform),
                findProfile.getUserId(),
                String.join(",", RequestParam.CASUAL_PVP)
        );

        String responseCasualPvp = getDataUsingApi(casualPvpUrl);
        return parseGeneralTemplateUrlResponse(responseCasualPvp, findProfile.getUserId());
    }

    public String getRankPvp(String platform, String id) {
        Profile findProfile = getProfile(platform, id);
        String rankPvpUrl = String.format(GENERAL_URL_TEMPLATE,
                Platform.platformToSpaceId(platform),
                Platform.platformToPlatformId(platform),
                findProfile.getUserId(),
                String.join(",", RequestParam.RANK_PVP)
        );

        String responseRankPvp = getDataUsingApi(rankPvpUrl);
        return parseGeneralTemplateUrlResponse(responseRankPvp, findProfile.getUserId());
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



    private String parseGeneralTemplateUrlResponse(String response, String userId) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        jsonObject = jsonObject.getAsJsonObject("results").getAsJsonObject(userId);
        if(jsonObject == null) {
            throw new R6NotFoundPlayerProfileException("Not found player id or platform");
        }
        String parsedStr = jsonObject.get(userId).toString();
        return parsedStr;
    }


    public Profile getProfile(String platform, String id) {
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
}
