package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.r6api.*;
import org.example.springboot.service.operators.RankStatService;
import org.example.springboot.web.dto.RankStatResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RankApiController {
    private final RankStatService rankStatService;

    @GetMapping("/api/v1/rank/{platform}/{id}")
    public RankStatResponseDto findById(@PathVariable String platform,
                                        @PathVariable String id,
                                        @RequestParam(required = false, defaultValue = "-1") int season) {

        return rankStatService.getRankStat(platform, id, season);
    }

}
