package org.example.springboot.web;

import org.example.springboot.r6api.API;
import org.example.springboot.r6api.AuthToken;
import org.example.springboot.r6api.GeneralPvp;
import org.example.springboot.r6api.UbiAuthApi;
import org.example.springboot.web.dto.GeneralResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralStatApiController {

    @GetMapping("/api/v1/general/{platform}/{id}")
    public GeneralResponseDto findById(@PathVariable String platform,
                                       @PathVariable String id) {
        AuthToken token = UbiAuthApi.getAuthToken();
        API api = new API(token);

        GeneralPvp pvp = api.getGenernalPvp(platform,id);
        return new GeneralResponseDto(pvp);
    }
}
