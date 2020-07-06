package org.example.springboot.r6api;

import org.example.springboot.exception.r6api.R6BadAuthenticationException;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.Map;

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

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void When_WrongPw_Expect_R6AuthException() {
        exceptionRule.expect(R6BadAuthenticationException.class);
        exceptionRule.expectMessage(Matchers.containsString("401"));

        ubiAuthApi.setPw("somethingwrong");
        ubiAuthApi.getAuthToken();
    }
}

