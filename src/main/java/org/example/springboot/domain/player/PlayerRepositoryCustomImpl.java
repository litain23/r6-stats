package org.example.springboot.domain.player;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerRepositoryCustomImpl implements PlayerRepositoryCustom {
    private final PlayerRepositoryBasic playerRepositoryBasic;

    @Override
    public Player getPlayerIfNotExistReturnNewEntity(String platform, String playerId) {
        Player player = playerRepositoryBasic.findByPlatformAndAndPlayerId(platform, playerId);
        if(player == null) {
            player = Player.builder()
                    .platform(platform)
                    .playerId(playerId)
                    .build();
            playerRepositoryBasic.save(player);
        }
        return player;
    }
}
