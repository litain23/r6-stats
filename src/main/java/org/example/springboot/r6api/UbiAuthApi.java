package org.example.springboot.r6api;

import com.google.gson.Gson;
import org.example.springboot.exception.r6api.R6BadAuthenticationException;
import org.example.springboot.exception.r6api.R6ErrorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@PropertySource("classpath:ubi-login.properties")
@Component
public class UbiAuthApi {
    private final static String LOGIN_API_URL = "https://public-ubiservices.ubi.com/v3/profiles/sessions";
    public final static String UPP_APP_ID = "39baebad-39e5-4552-8c25-2c9b919064e2";
    private AuthToken token = null;

    @Value("${ubi.email}")
    private String email;

    @Value("${ubi.pw}")
    private String pw;

    private String encodeBase64(String email, String pw) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString((email+":"+pw).getBytes("UTF-8"));
    }

    public AuthToken getAuthToken() {
        if(token != null && checkTokenSessionTime()) {
            return token;
        }

        try {
            String encodedIdPw = encodeBase64(email, pw);

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
                token = new Gson().fromJson(br.readLine(), AuthToken.class);
                return token;
            } else if(responseCode == HttpURLConnection.HTTP_UNAUTHORIZED || responseCode == HttpURLConnection.HTTP_FORBIDDEN) {
                throw new R6BadAuthenticationException("fail to login ubi-soft");
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                System.out.println(br);
                throw new R6ErrorException("fail to get Authentication Token");
            }
        } catch (UnsupportedEncodingException e) {
            throw new R6ErrorException("encode fail");
        } catch (IOException e) {
            throw new R6ErrorException("fail to get Authentication Token");
        }
    }

    private boolean checkTokenSessionTime() {
        String expirationTimeStr = token.getExpiration().split("\\.")[0];
        ZoneId UTC_1 = ZoneId.of("UTC+1");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime expirationTime = LocalDateTime.parse(expirationTimeStr, formatter);
        // 세션 기간 만료는 3시간이지만, 2시간마다 갱신
        LocalDateTime now = LocalDateTime.now(UTC_1);

        if(now.isBefore(expirationTime)) {
            return true;
        } else {
            return false;
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
