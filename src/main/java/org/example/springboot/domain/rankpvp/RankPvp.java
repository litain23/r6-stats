package org.example.springboot.domain.rankpvp;

import lombok.Builder;
import lombok.Getter;
import org.example.springboot.domain.player.Player;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class RankPvp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="PLAYER_ID")
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
    public RankPvp(int death, int kills, int matchLost, int matchWon, int matchPlayed, int timePlayed) {
        this.death = death;
        this.kills = kills;
        this.matchLost = matchLost;
        this.matchPlayed = matchPlayed;
        this.matchWon = matchWon;
        this.timePlayed = timePlayed;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
