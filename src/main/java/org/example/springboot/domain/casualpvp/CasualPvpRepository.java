package org.example.springboot.domain.casualpvp;

import org.example.springboot.domain.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CasualPvpRepository extends JpaRepository<CasualPvp, Long> {
    List<CasualPvp> findByPlayer(Player player);
}
