package me.r6_search.web;

import lombok.RequiredArgsConstructor;
import me.r6_search.config.PlayerAnnotation;
import me.r6_search.domain.player.Player;
import me.r6_search.service.PlayerService;
import me.r6_search.web.dto.ProfileResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PlayerProfileController {
    private final PlayerService playerService;

    @GetMapping("/api/v1/profile/{platform}/{id}")
    public ProfileResponseDto getPlayerProfile(@PlayerAnnotation Player player) {
        return new ProfileResponseDto(player.getProfileId());
    }
}
