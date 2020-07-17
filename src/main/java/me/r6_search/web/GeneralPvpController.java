package me.r6_search.web;

import lombok.RequiredArgsConstructor;
import me.r6_search.config.PlayerAnnotation;
import me.r6_search.domain.player.Player;
import me.r6_search.service.GeneralPvpService;
import me.r6_search.web.dto.r6api.GeneralPvpResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GeneralPvpController {
    private final GeneralPvpService generalPvpService;

    @GetMapping("/api/v1/generalpvp/{platform}/{id}")
    public GeneralPvpResponseDto findById(@PlayerAnnotation Player player) {
        return new GeneralPvpResponseDto(generalPvpService.getGeneralPvp(player));
    }
}
