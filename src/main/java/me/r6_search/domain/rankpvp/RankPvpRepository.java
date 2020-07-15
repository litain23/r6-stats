package me.r6_search.domain.rankpvp;

import me.r6_search.domain.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankPvpRepository extends JpaRepository<RankPvp, Long> {
    List<RankPvp> findByPlayer(Player player);
}
