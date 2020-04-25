package org.example.springboot.security;

import org.example.springboot.service.GeneralPvpService;
import org.example.springboot.service.OperatorsService;
import org.example.springboot.service.RankStatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WebSecurityConfigTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GeneralPvpService generalPvpService;
    @MockBean
    private OperatorsService operatorsService;
    @MockBean
    private RankStatService rankStatService;


    @Test
    public void When_Access_Public_Page_Expect_OK() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void When_Access_Private_Page_Expect_Unauthorized() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/*"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

}
