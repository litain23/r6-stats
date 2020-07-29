package me.r6_search.model.operatordescription;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class OperatorDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;
    long nameOasisId;
    long ctuOasisId;
    String operatorIndex;
    String category;
    String statisticPvpId;
    String statisticPveId;
    long statisticPvpOasisId;
    long statisticPveOasisId;
}
