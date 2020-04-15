package org.example.springboot.r6api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;
import java.util.Map;

import com.google.gson.*;

import javax.swing.plaf.IconUIResource;

public class UbiAuthApi {
    private final static String LOGIN_API_URL = "https://public-ubiservices.ubi.com/v3/profiles/sessions";
    private static UbiAuthApi ubiAuthApi = new UbiAuthApi();
    public final static String UPP_APP_ID = "39baebad-39e5-4552-8c25-2c9b919064e2";


    private static String encodeBase64(String email, String pw) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString((email+":"+pw).getBytes("UTF-8"));
    }

    public static AuthToken getAuthToken() {
        try {
            JsonObject loginIdPw = new Gson().fromJson(new FileReader("ubi-login.json"), JsonObject.class);
            String email = loginIdPw.get("email").getAsString();
            String passwd = loginIdPw.get("pw").getAsString();
            String encodedIdPw = encodeBase64(email, passwd);

            URL url = new URL(LOGIN_API_URL);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Basic " + encodedIdPw);
            conn.setRequestProperty("Ubi-AppId", UPP_APP_ID);
            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestProperty("Content-Length", "0");

            OutputStream os = conn.getOutputStream();
            os.write("{}".getBytes());
            os.close();

            int responseCode = conn.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_ACCEPTED || responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                return new Gson().fromJson(br.readLine(), AuthToken.class);
            } else {
                return null;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
