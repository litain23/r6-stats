package org.example.springboot.domain.rankpvp;

import org.example.springboot.domain.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankPvpRepository extends JpaRepository<RankPvp, Long> {
    List<RankPvp> findByPlayer(Player player);
}
