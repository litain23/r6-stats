package org.example.springboot.domain.seasonoperator;

import lombok.Builder;
import lombok.Getter;
import org.example.springboot.domain.operator.Operator;
import org.example.springboot.domain.player.Player;
import org.example.springboot.r6api.dto.OperatorDto;
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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"player_id", "season"})})
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

    @NotNull
    int season;

    @CreationTimestamp
    LocalDateTime createdTime;

    public SeasonOperator() {}

    @Builder
    public SeasonOperator(Player player, int season, List<Operator> operatorList) {
        this.player = player;
        this.season = season;
        this.operatorList = operatorList;
    }
}
