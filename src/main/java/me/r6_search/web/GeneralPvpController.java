package me.r6_search.web;

import lombok.RequiredArgsConstructor;
import me.r6_search.config.PlayerAnnotation;
import me.r6_search.model.player.Player;
import me.r6_search.service.GeneralPvpService;
import me.r6_search.web.dto.r6api.GeneralPvpResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/stat")
@RestController
public class GeneralPvpController {
    private final GeneralPvpService generalPvpService;

    @GetMapping("/generalpvp/{platform}/{id}")
    public GeneralPvpResponseDto findById(@PlayerAnnotation Player player) {
        return new GeneralPvpResponseDto(generalPvpService.getGeneralPvp(player));
    }
}
