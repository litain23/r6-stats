package org.example.springboot.domain.seasonoperator;

import org.example.springboot.domain.operator.Operator;
import org.example.springboot.domain.weeklyoperator.WeeklyOperator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeasonOperatorRepository extends JpaRepository<SeasonOperator, Long> {
    List<Operator> findBySeason(int season);
}
