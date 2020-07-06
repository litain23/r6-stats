package org.example.springboot.domain.seasonoperator;

import org.example.springboot.domain.operator.Operator;
import org.example.springboot.domain.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeasonOperatorRepository extends JpaRepository<SeasonOperator, Long> {
    List<Operator> findByPlayerAnAndSeason(Player player, int season);
}
