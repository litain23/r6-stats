package org.example.springboot.domain.casualpvp;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.example.springboot.domain.player.Player;
import org.example.springboot.r6api.dto.CasualPvpDto;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ToString
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class CasualPvp {
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

    public CasualPvp() { }

    @Builder
    public CasualPvp(int death, int kills, int matchLost, int matchWon, int matchPlayed, int timePlayed, Player player) {
        this.death = death;
        this.kills = kills;
        this.matchLost = matchLost;
        this.matchPlayed = matchPlayed;
        this.matchWon = matchWon;
        this.timePlayed = timePlayed;
        this.player = player;
    }

    public CasualPvp(CasualPvpDto dto, Player player) {
        this.death = dto.getDeath();
        this.kills = dto.getKills();
        this.matchLost = dto.getMatchLost();
        this.matchWon = dto.getMatchWon();
        this.matchPlayed = dto.getMatchPlayed();
        this.timePlayed = dto.getTimePlayed();
        this.player = player;
    }
}
