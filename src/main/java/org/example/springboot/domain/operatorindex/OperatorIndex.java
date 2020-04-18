package org.example.springboot.domain.operatorindex;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class OperatorIndex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int nameOasisId;

    @Column
    private int ctuOasisId; // 오퍼레이터 소속 id

    @Column
    private String index;

    @Column
    private String category;

    @Column
    private String statisticPvpId;

    @Column
    private String statisticPveId;

    @Column
    private int statisticPvpOasisId;

    @Column
    private int statisticPveOasisId;

    public OperatorIndex() {
    }

    @Builder
    public OperatorIndex(String name, int nameOasisId, int ctuOasisId, String index, String category, String statisticPvpId, String statisticPveId, int statisticPvpOasisId, int statisticPveOasisId) {
        this.name = name;
        this.nameOasisId = nameOasisId;
        this.ctuOasisId = ctuOasisId;
        this.index = index;
        this.category = category;
        this.statisticPvpId = statisticPvpId;
        this.statisticPveId = statisticPveId;
        this.statisticPvpOasisId = statisticPvpOasisId;
        this.statisticPveOasisId = statisticPveOasisId;
    }
}