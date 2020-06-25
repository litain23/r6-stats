package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.player.Player;
import org.example.springboot.service.PlayerService;
import org.example.springboot.web.dto.ProfileResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PlayerProfileController {
    private final PlayerService playerService;

    @GetMapping("/api/v1/profile/{platform}/{id}")
    public ProfileResponseDto getPlayerProfile(@PathVariable String platform,
                                               @PathVariable String id) {
        Player player = playerService.findPlayerIfNotExistReturnNewEntity(platform, id);
        return new ProfileResponseDto(player.getProfileId());
    }
}
