package org.example.springboot.domain.seasonoperator;

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
public class SeasonOperator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="player_id")
    @NotNull
    private Player player;

    @OneToMany
    private List<Operator> operatorList = new ArrayList<>();

    @NotBlank
    int season;

    @CreationTimestamp
    LocalDateTime createdTime;

    public SeasonOperator() {}

    @Builder
    public SeasonOperator(Player player, int season) {
        this.player = player;
        this.season = season;
    }

    public void addOperator(Operator op) {
        this.operatorList.add(op);
    }
}
