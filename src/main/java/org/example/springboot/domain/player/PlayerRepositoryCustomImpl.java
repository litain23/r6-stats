package org.example.springboot.domain.player;

import lombok.RequiredArgsConstructor;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.dto.ProfileDto;

@RequiredArgsConstructor
public class PlayerRepositoryCustomImpl implements PlayerRepositoryCustom {
    private final PlayerRepositoryBasic playerRepositoryBasic;

    private final UbiApi ubiApi;

    @Override
    public Player getPlayerIfNotExistReturnNewEntity(String platform, String userId) {

        Player player = playerRepositoryBasic.findByPlatformAndAndUserId(platform, userId);
        if(player == null) {
            // 아이디를 변경한 경우, profileId로 찾아서 있는 경우 변경 후 해당 player 리턴
            ProfileDto profileDto = ubiApi.getProfile(platform, userId);
            String profileId = profileDto.getProfileId();
            player = playerRepositoryBasic.findByPlatformAndProfileId(platform, profileId);

            // profileId 로 찾은 경우에 도 없는 경우 => 처음 등록한 유저
            if(player == null) {
                player = Player.builder()
                        .platform(platform)
                        .userId(userId)
                        .profileId(profileId)
                        .build();
                playerRepositoryBasic.save(player);
            }
        }
        return player;
    }
}
