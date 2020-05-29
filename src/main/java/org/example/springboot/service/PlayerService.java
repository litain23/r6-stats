package org.example.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.dto.ProfileDto;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final UbiApi ubiApi;

    public Player findPlayerIfNotExistReturnNewEntity(String platform, String id) {
        ProfileDto profileDto = ubiApi.getProfile(platform, id);
        Player player = playerRepository.findByPlatformAndProfileId(platform, profileDto.getUserId());
        if(player == null) {
            player = Player.builder()
                    .profileId(profileDto.getUserId())
                    .platform(platform)
                    .userId(id)
                    .build();
        }

        if(player.getUserId() != id) {
            // user 가 id 를 바꾼 경우, id 업데이트
            player.updateUserId(id);
        }

        playerRepository.save(player);
        return player;
    }
}
