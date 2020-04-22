package org.example.springboot.r6api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Map;

import com.google.gson.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.swing.plaf.IconUIResource;

public class UbiAuthApi {
    private final static String LOGIN_API_URL = "https://public-ubiservices.ubi.com/v3/profiles/sessions";
    private static UbiAuthApi ubiAuthApi = new UbiAuthApi();
    public final static String UPP_APP_ID = "39baebad-39e5-4552-8c25-2c9b919064e2";
    private static AuthToken token = null;


    private static String encodeBase64(String email, String pw) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString((email+":"+pw).getBytes("UTF-8"));
    }

    public static AuthToken getAuthToken() {
        if(token != null && checkTokenSessionTime()) {
            return token;
        }

        try {
            String encodedIdPw = getEncodedIdPw();

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
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "ubi-soft login failed");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }  catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getEncodedIdPw() throws FileNotFoundException, UnsupportedEncodingException{
        try {
            Map<String, String> loginIdPwMap = new Gson().fromJson(new FileReader("ubi-login.json"), Map.class);
            String email = loginIdPwMap.get("email");
            String passwd = loginIdPwMap.get("pw");
            return encodeBase64(email, passwd);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("ubi-login.json don't exist");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEncodingException("Unsupported encoding");
        }
    }


    private static boolean checkTokenSessionTime() {
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
}
