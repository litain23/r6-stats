package me.r6_search.domain.casualpvp;

import me.r6_search.domain.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CasualPvpRepository extends JpaRepository<CasualPvp, Long> {
    List<CasualPvp> findByPlayer(Player player);
}
