package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.service.RankPvpService;
import org.example.springboot.web.dto.RankPvpResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RankPvpController {
    private final RankPvpService RankPvpService;

    @GetMapping("/api/v1/rankpvp/{platform}/{id}/all")
    public List<RankPvpResponseDto> getRankPvpAll(@PathVariable String platform,
                                                      @PathVariable String id) {

        return RankPvpService.getRankPvpAll(platform, id);
    }

    @GetMapping("/api/v1/rankpvp/{platform}/{id}")
    public RankPvpResponseDto getRankPvp(@PathVariable String platform,
                                         @PathVariable String id) {
        return RankPvpService.getRankPvp(platform, id);
    }
}