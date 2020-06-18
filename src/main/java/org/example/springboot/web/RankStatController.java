package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.r6api.dto.RankStatDto;
import org.example.springboot.service.RankStatService;
import org.example.springboot.web.dto.RankStatRegionResponseDto;
import org.example.springboot.web.dto.RankStatRequestDto;
import org.example.springboot.web.dto.RankStatResponseDto;
import org.example.springboot.web.dto.RankStatSeasonResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class RankStatController {
    private final RankStatService rankStatService;

    @GetMapping("/api/v1/rank/{platform}/{id}/all")
    public List<RankStatSeasonResponseDto> getRankStatAllSeason(@PathVariable String platform,
                                                                @PathVariable String id) {
        return rankStatService.getRankStatAllSeason(platform, id);
    }

    @GetMapping("/api/v1/rank/{platform}/{id}")
    public List<RankStatRegionResponseDto> getRankStat(@PathVariable String platform,
                                                       @PathVariable String id,
                                                       @RequestParam(defaultValue = "-1", required = false) int season) {
        return rankStatService.getRankStat(platform, id, season);
    }

}
