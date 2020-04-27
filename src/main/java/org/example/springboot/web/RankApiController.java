package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.service.RankStatService;
import org.example.springboot.web.dto.RankStatResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RankApiController {
    private final RankStatService rankStatService;

    @GetMapping("/api/v1/rank/{platform}/{id}/all")
    public List<RankStatResponseDto> getRankStatAllSeason(@PathVariable String platform,
                                              @PathVariable String id) {

        return rankStatService.getRankStatAllSeason(platform, id);
    }

    @GetMapping("/api/v1/rank/{platform}/{id}")
    public RankStatResponseDto getRankStat(@PathVariable String platform,
                                        @PathVariable String id) {

        return rankStatService.getRankStat(platform, id, -1);
    }

}
