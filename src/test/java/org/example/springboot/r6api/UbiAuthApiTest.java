package org.example.springboot.r6api;

import org.example.springboot.exception.r6api.R6BadAuthenticationException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

public class UbiAuthApiTest {
    private static UbiAuthApi ubiAuthApi = new UbiAuthApi();

    @BeforeClass
    public static UbiAuthApi setUpAuthApi() throws IOException {
        File file = new File("src/main/resources/ubi-login.properties");
        FileReader fileReader = new FileReader(file);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String email = bufferedReader.readLine();
        email = email.split("=")[1];
        String pw= bufferedReader.readLine();
        pw = pw.split("=")[1];

        ubiAuthApi.setEmail(email);
        ubiAuthApi.setPw(pw);

        bufferedReader.close();;
        fileReader.close();;

        return ubiAuthApi;
    }

    @Test
    public void When_GetAuthToken_Expect_Good() {
        ubiAuthApi.getAuthToken();
    }

    @Test
    public void When_GetAuthTokenInExpirationTime_Expect_SameToken() {
        AuthToken token = ubiAuthApi.getAuthToken();
        AuthToken afterToken = ubiAuthApi.getAuthToken();

        assertThat(token).isEqualTo(afterToken);
    }

    @Test(expected = R6BadAuthenticationException.class)
    public void When_WrongPw_Expect_R6AuthException() {
        ubiAuthApi.setPw("somethingwrong");
        ubiAuthApi.getAuthToken();
    }
}

