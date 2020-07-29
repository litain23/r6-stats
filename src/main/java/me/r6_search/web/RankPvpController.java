package me.r6_search.web;

import lombok.RequiredArgsConstructor;
import me.r6_search.config.PlayerAnnotation;
import me.r6_search.web.dto.r6api.RankPvpResponseDto;
import me.r6_search.model.player.Player;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/stat")
@RestController
public class RankPvpController {
    private final me.r6_search.service.RankPvpService RankPvpService;

    @GetMapping("/rankpvp/{platform}/{id}/all")
    public List<RankPvpResponseDto> getRankPvpAll(@PlayerAnnotation Player player) {

        return RankPvpService.getRankPvpAll(player)
                .stream()
                .map(RankPvpResponseDto::new)
                .collect(Collectors.toList());

    }

    @GetMapping("/rankpvp/{platform}/{id}")
    public RankPvpResponseDto getRankPvp(@PlayerAnnotation Player player) {
        return new RankPvpResponseDto(RankPvpService.getRankPvp(player));
    }
}