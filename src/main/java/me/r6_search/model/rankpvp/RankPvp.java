package me.r6_search.model.rankpvp;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import me.r6_search.model.player.Player;
import me.r6_search.r6api.dto.RankPvpDto;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ToString
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class RankPvp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="PLAYER_ID")
    @NotNull
    private Player player;

    int death;
    int kills;
    int matchLost;
    int matchWon;
    int matchPlayed;
    int timePlayed;

    @CreatedDate
    private LocalDateTime createdTime;

    public RankPvp() { }

    @Builder
    public RankPvp(RankPvpDto rankPvpDto, Player player) {
        this.death = rankPvpDto.getDeath();
        this.kills = rankPvpDto.getKills();
        this.matchLost = rankPvpDto.getMatchLost();
        this.matchWon = rankPvpDto.getMatchWon();
        this.matchPlayed = rankPvpDto.getMatchPlayed();
        this.timePlayed = rankPvpDto.getTimePlayed();
        this.player = player;
    }
}
