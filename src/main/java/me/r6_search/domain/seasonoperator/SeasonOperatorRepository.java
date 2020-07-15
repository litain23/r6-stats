package me.r6_search.domain.seasonoperator;

import me.r6_search.domain.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonOperatorRepository extends JpaRepository<SeasonOperator, Long> {
    SeasonOperator findByPlayerAndSeason(Player player, int season);
}
