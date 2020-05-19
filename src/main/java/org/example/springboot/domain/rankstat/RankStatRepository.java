package org.example.springboot.domain.rankstat;

import org.example.springboot.domain.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RankStatRepository extends JpaRepository<RankStat, Long> {
    Optional<List<RankStat>> findByRegionAndPlayer(String region, Player player);
}
