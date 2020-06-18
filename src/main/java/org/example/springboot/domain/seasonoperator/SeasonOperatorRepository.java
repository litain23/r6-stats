package org.example.springboot.domain.seasonoperator;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonOperatorRepository extends JpaRepository<SeasonOperator, Long> {
    SeasonOperator findBySeason(int season);
}
