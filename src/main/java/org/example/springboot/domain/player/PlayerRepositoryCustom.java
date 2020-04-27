package org.example.springboot.domain.player;

public interface PlayerRepositoryCustom {
    Player getPlayerIfNotExistReturnNewEntity(String platform, String playerId);
}
