package org.example.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.dto.ProfileDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final UbiApi ubiApi;

    public Player findPlayerIfNotExistReturnNewEntity(String platform, String id) {
        Player player = playerRepository.findByPlatformAndAndUserId(platform, id);
        if(player == null) {
            ProfileDto profileDto = ubiApi.getProfile(platform, id);
            Player playerUsingProfileId = playerRepository.findByPlatformAndProfileId(platform, profileDto.getUserId());
            // 고유값이 profileId 로 찾는다 - 아이디가 변경될 수도 있기 때문
            if(playerUsingProfileId == null) {
                player = Player.builder()
                        .profileId(profileDto.getUserId())
                        .platform(platform)
                        .userId(id)
                        .build();

                playerRepository.save(player);
            } else {
                playerUsingProfileId.updateUserId(id);
            }
        }

        return player;
    }
}
