package org.example.springboot.r6api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UbiAuthApiTest {
    @Autowired
    UbiAuthApi ubiAuthApi;

    @Test
    public void When_Get_Ubi_Token_Expect_Authorized_Token() {
        // 정상작동하는가
        AuthToken token = ubiAuthApi.getAuthToken();
    }

    @Test
    public void When_Get_Ubi_Token_Not_Expiration_State_Expect_Return_Origin_Token() {
        //given
        AuthToken originToken = ubiAuthApi.getAuthToken();

        //when
        AuthToken newToken = ubiAuthApi.getAuthToken();

        //then
        assertThat(originToken).isEqualTo(newToken);
    }
}

