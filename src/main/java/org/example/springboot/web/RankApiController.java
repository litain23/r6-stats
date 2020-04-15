package org.example.springboot.web;

import org.example.springboot.r6api.*;
import org.example.springboot.web.dto.RankResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RankApiController {

    @GetMapping("/api/v1/rank/{platform}/{id}")
    public RankResponseDto findById(@PathVariable String platform,
                                    @PathVariable String id,
                                    @RequestParam(required = false, defaultValue = "-1") int season) {

        AuthToken token = UbiAuthApi.getAuthToken();
        API api = new API(token);

        RankStat rankStat = api.getRank(platform, id, season);
        return new RankResponseDto(rankStat);
    }

}
