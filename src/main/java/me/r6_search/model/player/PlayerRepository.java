package me.r6_search.model.player;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByPlatformAndAndUserId(String platform, String userId);
    Player findByPlatformAndProfileId(String platform, String profileId);
}
