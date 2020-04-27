package org.example.springboot.domain.player;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepositoryBasic extends JpaRepository<Player, Long> {
    Player findByPlatformAndAndPlayerId(String platform, String playerId);
}
