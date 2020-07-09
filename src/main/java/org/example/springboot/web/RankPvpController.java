package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.config.PlayerAnnotation;
import org.example.springboot.domain.player.Player;
import org.example.springboot.service.RankPvpService;
import org.example.springboot.web.dto.RankPvpResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class RankPvpController {
    private final RankPvpService RankPvpService;

    @GetMapping("/api/v1/rankpvp/{platform}/{id}/all")
    public List<RankPvpResponseDto> getRankPvpAll(@PlayerAnnotation Player player) {

        return RankPvpService.getRankPvpAll(player)
                .stream()
                .map(RankPvpResponseDto::new)
                .collect(Collectors.toList());

    }

    @GetMapping("/api/v1/rankpvp/{platform}/{id}")
    public RankPvpResponseDto getRankPvp(@PlayerAnnotation Player player) {
        return new RankPvpResponseDto(RankPvpService.getRankPvp(player));
    }
}