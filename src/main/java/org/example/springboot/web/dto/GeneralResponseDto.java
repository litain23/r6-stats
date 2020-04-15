package org.example.springboot.web.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import org.example.springboot.r6api.GeneralPvp;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class GeneralResponseDto {
    private int generalpvpMatchlost;
    private int generalpvpDeath;
    private int generalpvpPenetrationkills;
    private int generalpvpMatchwon;
    private int generalpvpBullethit;
    private int generalpvpMeleekills;
    private int generalpvpKillassists;
    private int generalpvpMatchplayed;
    private int generalpvpTimeplayed;
    private int generalpvpRevive;
    private int generalpvpKills;
    private int generalpvpHeadshot;

    public GeneralResponseDto(GeneralPvp pvp) {
        this.generalpvpMatchlost = pvp.getGeneralpvpMatchlost();
        this.generalpvpDeath = pvp.getGeneralpvpDeath();
        this.generalpvpPenetrationkills = pvp.getGeneralpvpPenetrationkills();
        this.generalpvpMatchwon = pvp.getGeneralpvpMatchwon();
        this.generalpvpBullethit = pvp.getGeneralpvpBullethit();
        this.generalpvpMeleekills = pvp.getGeneralpvpMeleekills();
        this.generalpvpKillassists = pvp.getGeneralpvpKillassists();
        this.generalpvpMatchplayed = pvp.getGeneralpvpMatchplayed();
        this.generalpvpTimeplayed = pvp.getGeneralpvpTimeplayed();
        this.generalpvpRevive = pvp.getGeneralpvpRevive();
        this.generalpvpKills = pvp.getGeneralpvpKills();
        this.generalpvpHeadshot = pvp.getGeneralpvpHeadshot();
    }
}
