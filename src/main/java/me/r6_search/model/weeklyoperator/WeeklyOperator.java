package me.r6_search.model.weeklyoperator;

import lombok.Builder;
import lombok.Getter;
import me.r6_search.model.operator.Operator;
import me.r6_search.model.player.Player;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operator> operatorList = new ArrayList<>();

    @NotNull
    private int week;

    @CreationTimestamp
    LocalDateTime createdTime;

    public WeeklyOperator() {}

    @Builder
    public WeeklyOperator(Player player, int week) {
        this.player = player;
        this.week = week;
    }

    public void addOperator(Operator op) {
        this.operatorList.add(op);
    }
}
