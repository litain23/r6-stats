package org.example.springboot.domain.rankpvp;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.example.springboot.domain.player.Player;
import org.example.springboot.r6api.dto.RankPvpDto;
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
    public RankPvp(int death, int kills, int matchLost, int matchWon, int matchPlayed, int timePlayed, Player player) {
        this.death = death;
        this.kills = kills;
        this.matchLost = matchLost;
        this.matchPlayed = matchPlayed;
        this.matchWon = matchWon;
        this.timePlayed = timePlayed;
        this.player = player;
    }

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
