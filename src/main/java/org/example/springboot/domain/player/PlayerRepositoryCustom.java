package org.example.springboot.domain.player;

public interface PlayerRepositoryCustom {
    public Player getPlayerIfNotExistReturnNewEntity(String platform, String playerId);
}
