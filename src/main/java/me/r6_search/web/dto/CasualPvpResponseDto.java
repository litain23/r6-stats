package me.r6_search.web.dto;

import lombok.Data;
import me.r6_search.domain.casualpvp.CasualPvp;
import me.r6_search.r6api.dto.CasualPvpDto;

import java.time.LocalDateTime;

@Data
public class CasualPvpResponseDto {
    int death;
    int kills;
    int matchLost;
    int matchWon;
    int matchPlayed;
    int timePlayed;
    LocalDateTime createdTime;

    public CasualPvpResponseDto(CasualPvpDto casualPvpDto) {
        this.death = casualPvpDto.getDeath();
        this.kills = casualPvpDto.getKills();
        this.matchLost = casualPvpDto.getMatchLost();
        this.matchWon = casualPvpDto.getMatchWon();
        this.matchPlayed = casualPvpDto.getMatchPlayed();
        this.timePlayed = casualPvpDto.getTimePlayed();
        this.createdTime = casualPvpDto.getCreatedTime();
    }

    public CasualPvpResponseDto(CasualPvp casualPvp) {
        this.death = casualPvp.getDeath();
        this.kills = casualPvp.getKills();
        this.matchLost = casualPvp.getMatchLost();
        this.matchWon = casualPvp.getMatchWon();
        this.matchPlayed = casualPvp.getMatchPlayed();
        this.timePlayed = casualPvp.getTimePlayed();
        this.createdTime = casualPvp.getCreatedTime();
    }
}
