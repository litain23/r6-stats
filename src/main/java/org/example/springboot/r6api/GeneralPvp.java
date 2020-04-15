package org.example.springboot.r6api;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GeneralPvp {
    @SerializedName("generalpvp_matchlost:infinite")
    private int generalpvpMatchlost;
    @SerializedName("generalpvp_death:infinite")
    private int generalpvpDeath;
    @SerializedName("generalpvp_penetrationkills:infinite")
    private int generalpvpPenetrationkills;
    @SerializedName("generalpvp_matchwon:infinite")
    private int generalpvpMatchwon;
    @SerializedName("generalpvp_bullethit:infinite")
    private int generalpvpBullethit;
    @SerializedName("generalpvp_meleekills:infinite")
    private int generalpvpMeleekills;
    @SerializedName("generalpvp_killassists:infinite")
    private int generalpvpKillassists;
    @SerializedName("generalpvp_matchplayed:infinite")
    private int generalpvpMatchplayed;
    @SerializedName("generalpvp_timeplayed:infinite")
    private int generalpvpTimeplayed;
    @SerializedName("generalpvp_revive:infinite")
    private int generalpvpRevive;
    @SerializedName("generalpvp_kills:infinite")
    private int generalpvpKills;
    @SerializedName("generalpvp_headshot:infinite")
    private int generalpvpHeadshot;
}
