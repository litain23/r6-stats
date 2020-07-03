package org.example.springboot.r6api;

import org.example.springboot.exception.r6api.R6BadAuthenticationException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.env.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import static java.lang.System.getenv;
import static org.assertj.core.api.Assertions.assertThat;

public class UbiAuthApiTest {
    private UbiAuthApi ubiAuthApi;

    public static UbiAuthApi getUbiAuthApi() {
        UbiAuthApi ubiAuthApi = new UbiAuthApi();
        Map<String, String> envVariables = System.getenv();
        ubiAuthApi.setEmail(envVariables.get("ubi_email"));
        ubiAuthApi.setPw(envVariables.get("ubi_pw"));
        return ubiAuthApi;
    }

    @Before
    public void setUpAuthApi() {
        ubiAuthApi = getUbiAuthApi();
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

