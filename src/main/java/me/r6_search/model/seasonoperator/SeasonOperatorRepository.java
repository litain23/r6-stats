package me.r6_search.model.seasonoperator;

import me.r6_search.model.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonOperatorRepository extends JpaRepository<SeasonOperator, Long> {
    SeasonOperator findByPlayerAndSeason(Player player, int season);
}
