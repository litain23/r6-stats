package org.example.springboot.domain.player;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerRepositoryCustomImpl implements PlayerRepositoryCustom {
    private final PlayerRepositoryBasic playerRepositoryBasic;

    @Override
    public Player getPlayerIfNotExistReturnNewEntity(String platform, String userId) {
        Player player = playerRepositoryBasic.findByPlatformAndAndUserId(platform, userId);
        if(player == null) {
            player = Player.builder()
                    .platform(platform)
                    .userId(userId)
                    .build();
            playerRepositoryBasic.save(player);
        }
        return player;
    }
}
