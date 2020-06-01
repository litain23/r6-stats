package org.example.springboot.domain.weeklyoperator;

import lombok.Builder;
import lombok.Getter;
import org.example.springboot.domain.operator.Operator;
import org.example.springboot.domain.player.Player;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class WeeklyOperator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="player_id")
    @NotNull
    private Player player;

    @OneToMany(mappedBy = "weeklyOperator")
    private List<Operator> operatorList = new ArrayList<>();

    private int season;

    private int week;

    @CreationTimestamp
    LocalDateTime createdTime;

    public WeeklyOperator() {}

    @Builder
    public WeeklyOperator(Player player, int season, int week) {
        this.player = player;
        this.season = season;
        this.week = week;
    }
}
