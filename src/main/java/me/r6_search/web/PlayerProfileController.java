package me.r6_search.web;

import lombok.RequiredArgsConstructor;
import me.r6_search.config.PlayerAnnotation;
import me.r6_search.model.player.Player;
import me.r6_search.service.PlayerService;
import me.r6_search.web.dto.r6api.ProfileResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/stat")
@RestController
public class PlayerProfileController {
    private final PlayerService playerService;

    @GetMapping("/profile/{platform}/{id}")
    public ProfileResponseDto getPlayerProfile(@PlayerAnnotation Player player) {
        return new ProfileResponseDto(player.getProfileId());
    }
}
