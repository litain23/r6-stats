package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.service.RankStatService;
import org.example.springboot.web.dto.RankStatResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RankStatController {
    private final RankStatService rankStatService;

    @GetMapping("/api/v1/rank/{platform}/{id}/all")
    public ResponseEntity<List<RankStatResponseDto>> getRankStatAllSeason(@PathVariable String platform,
                                                                         @PathVariable String id) {

        return ResponseEntity.ok(rankStatService.getRankStatAllSeason(platform, id));
    }

    @GetMapping("/api/v1/rank/{platform}/{id}")
    public ResponseEntity<RankStatResponseDto> getRankStat(@PathVariable String platform,
                                                    @PathVariable String id,
                                                    @RequestParam(defaultValue = "-1", required = false) int season) {

        return ResponseEntity.ok(rankStatService.getRankStat(platform, id, season));
    }

}
