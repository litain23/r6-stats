package org.example.springboot.domain.seasonoperator;

import org.example.springboot.domain.operator.Operator;
import org.example.springboot.domain.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeasonOperatorRepository extends JpaRepository<SeasonOperator, Long> {
    SeasonOperator findByPlayerAndSeason(Player player, int season);
}
