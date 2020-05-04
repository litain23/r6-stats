package org.example.springboot.domain.player;

import lombok.Builder;
import lombok.Getter;
import org.example.springboot.domain.generalpvp.GeneralPvp;
import org.example.springboot.domain.operators.Operators;
import org.example.springboot.domain.rankstat.RankStat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String playerId;

    @Column(nullable = false)
    private String platform;

    @OneToMany(mappedBy = "player")
    private List<Operators> operatorsList = new ArrayList<>();

    @OneToMany(mappedBy = "player")
    private List<RankStat> rankList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "generalpvp_id")
    private GeneralPvp generalPvp;

    public Player() { }

    @Builder
    public Player(String platform, String playerId) {
        this.platform = platform;
        this.playerId = playerId;
    }

    public void setGeneralPvp(GeneralPvp generalPvp) {
        this.generalPvp = generalPvp;
    }
}
