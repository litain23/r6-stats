package org.example.springboot.r6api;

import com.google.gson.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class API {
    private static final String RANK_URL_TEMPLATE = "https://public-ubiservices.ubi.com/v1/spaces/%s/sandboxes/%s/r6karma/players?board_id=pvp_ranked&profile_ids=%s&region_id=%s&season_id=%s";
    private static final String PROFILE_URL_TEMPLATE = "https://public-ubiservices.ubi.com/v2/profiles?platformType=%s&nameOnPlatform=%s";
    private static final String GENERAL_URL_TEMPLATE = "https://public-ubiservices.ubi.com/v1/spaces/%s/sandboxes/%s/playerstats2/statistics?populations=%s&statistics=%s";

    private AuthToken authToken;

    public API(AuthToken token) {
        this.authToken = token;
    }

    public GeneralPvp getGenernalPvp(String platform, String id) {
        Profile findIdProfile = getProfile(platform, id);
        String generalPvpUrl = String.format(GENERAL_URL_TEMPLATE,
                Platform.platformToSpaceId(platform),
                Platform.platformToPlatformId(platform),
                findIdProfile.getUserId(),
                String.join(",", RequestParam.GENERAL_PVP)
        );

        String responseGeneralPvp = getDataUsingApi(generalPvpUrl, authToken);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(responseGeneralPvp, JsonObject.class);
        jsonObject = jsonObject.getAsJsonObject("results");
        String generalPvpStr = jsonObject.get(findIdProfile.getUserId()).toString();
        return gson.fromJson(generalPvpStr, GeneralPvp.class);

    }

    public Profile getProfile(String platform, String id) {
        String profileUrl = String.format(PROFILE_URL_TEMPLATE, platform, id);
        String responseProfile = getDataUsingApi(profileUrl, authToken);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(responseProfile, JsonObject.class);
        JsonArray jsonArray =jsonObject.get("profiles").getAsJsonArray();
        return gson.fromJson(jsonArray.get(0), Profile.class);
    }

    public RankStat getRank(String platform, String id, int season) {
        Profile findIdProfile = getProfile(platform, id);

        String region = "apac";
        String rankUrl =
                String.format(RANK_URL_TEMPLATE,
                            Platform.platformToSpaceId(platform),
                            Platform.platformToPlatformId(platform),
                            findIdProfile.getUserId(),
                            region,
                            season);

        String responseRankData = getDataUsingApi(rankUrl, authToken);
        Gson gson = new GsonBuilder()
                .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        JsonObject rankStat = gson.fromJson(responseRankData, JsonObject.class);
        rankStat = rankStat.get("players").getAsJsonObject().get(findIdProfile.getUserId()).getAsJsonObject();

        return gson.fromJson(rankStat, RankStat.class);
    }

    public RankStat getRank(String platform, String id) {
        return getRank(platform, id, -1);
    }


    private String getDataUsingApi(String requestUrl, AuthToken token) {
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
