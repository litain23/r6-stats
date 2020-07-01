package org.example.springboot.services;


import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.dto.ProfileDto;
import org.example.springboot.service.PlayerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {
    @InjectMocks
    PlayerService playerService;

    @Mock
    UbiApi ubiApi;

    @Mock
    PlayerRepository playerRepository;

    Player player;

    @Test
    public void When_FindPlayer_Return_Player() {
        String platform = "uplay";
        String userId = "piliot";
        String profileId = "test_profile_id";

        when(ubiApi.getProfile(platform, userId)).thenReturn(
                ProfileDto.builder()
                        .idOnPlatform(userId)
                        .nameOnPlatform(platform)
                        .platformType(platform)
                        .userId(profileId)
                        .profileId(profileId)
                        .build()
        );

        when(playerRepository.findByPlatformAndProfileId(platform, profileId)).thenReturn(
                player = Player.builder()
                        .platform(platform)
                        .profileId(profileId)
                        .userId(userId)
                        .build()
        );

        Player findPlayer  = playerService.findPlayerIfNotExistReturnNewEntity(platform, userId);
        assertThat(findPlayer).isEqualTo(player);
    }

    @Test
    public void When_FindPlayerDiffUserId_Return_Player() {
        String platform = "uplay";
        String userId = "piliot";
        String profileId = "test_profile_id";
        String userIdInDb = "something";

        when(ubiApi.getProfile(platform, userId)).thenReturn(
                ProfileDto.builder()
                        .idOnPlatform(userId)
                        .nameOnPlatform(platform)
                        .platformType(platform)
                        .userId(profileId)
                        .profileId(profileId)
                        .build()
        );

        when(playerRepository.findByPlatformAndProfileId(platform, profileId)).thenReturn(
                Player.builder()
                        .platform(platform)
                        .profileId(profileId)
                        .userId(userIdInDb) // DB 에서 가져올 땐, 예전 아이디 리턴
                        .build()
        );

        Player findPlayer = playerService.findPlayerIfNotExistReturnNewEntity(platform, userId);
        assertThat(findPlayer.getUserId()).isEqualTo(userId);
    }
}
