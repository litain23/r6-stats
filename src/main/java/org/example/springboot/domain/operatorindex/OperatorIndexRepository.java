package org.example.springboot.domain.operatorindex;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperatorIndexRepository extends JpaRepository<OperatorIndex, Long> {
    List<OperatorIndex> findByOperatorIndex(String operatorIndex);
}