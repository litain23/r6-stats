package org.example.springboot.r6api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;

import java.io.*;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.isEquals;

public class UbiAuthApiTest {

    @Test
    public void When_Get_Ubi_Token_Expect_Authorized_Token() {
        // 정상작동하는가
        AuthToken token = UbiAuthApi.getAuthToken();
    }

    @Test
    public void When_Get_Ubi_Token_Not_Expiration_State_Expect_Return_Origin_Token() {
        //given
        AuthToken originToken = UbiAuthApi.getAuthToken();

        //when
        AuthToken newToken = UbiAuthApi.getAuthToken();

        //then
        assertThat(originToken).isEqualTo(newToken);
    }

    @Test
    public void When_Give_Wrong_ID_PW_Get_Token_Expect_Unauthorized() throws Exception {
        // TODO: 예외처리에 대한 기준이 필요
        // 현재는 UbiTokenAuth 는 실패 시, null 를 던져줌
        // controller 에서 null 을 받으면 return null;
        // 그래서 테스트 시, exception 에 대한 검증 불가 -> throw 가 아니라 null 를 리턴하기 때문

        // Token 을 얻을 때, throw 로 던져주면 controller 에서 처리는 어떻게 해야하는가
        // 1. Interceptor 을 구현해서, 미리 모든 controller 에서 오기전에 처리
        //  * 1번의 방법은 API 를 호출할 때 적용불가능
        // 2. throw 던지고 controller 마다 try catch 로 잡는다 ..
        //  * test 에 용이 -> expected exception 에 관한 except 테스트 가능
        //  * 근데 왜인지 모르겠지만 개인적으로 맘에 안듬..
        Map<String, String> loginIdPwMap = null;
        File file = new File("ubi-login.json");
        loginIdPwMap = new Gson().fromJson(new FileReader(file), Map.class);

        String originEmail = loginIdPwMap.get("email");
        String originPasswd = loginIdPwMap.get("pw");

        loginIdPwMap.put("pw", "wrongpwd123");
        FileWriter fw = new FileWriter(file);

        fw.write(new Gson().toJson(loginIdPwMap));
        fw.close();

        AuthToken authToken = UbiAuthApi.getAuthToken();

        loginIdPwMap.put("pw", originPasswd);

        fw = new FileWriter(file);
        fw.write(new Gson().toJson(loginIdPwMap));
        fw.close();
    }
}
