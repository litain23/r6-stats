package org.example.springboot.domain.player;

import lombok.Builder;
import lombok.Getter;
import org.example.springboot.domain.casualpvp.CasualPvp;
import org.example.springboot.domain.operator.Operator;
import org.example.springboot.domain.rankpvp.RankPvp;
import org.example.springboot.domain.rankstat.RankStat;
import org.example.springboot.domain.seasonoperator.SeasonOperator;
import org.example.springboot.domain.weeklyoperator.WeeklyOperator;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"profileId", "platform"})})
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String userId;

    @NotBlank
    private String profileId;

    @NotBlank
    private String platform;

    @OneToMany(mappedBy = "player")
    private List<WeeklyOperator> weeklyOperatorList = new ArrayList<>();

    @OneToMany(mappedBy = "player")
    private List<SeasonOperator> seasonOperatorList = new ArrayList<>();

    @OneToMany(mappedBy = "player")
    private List<RankStat> rankList = new ArrayList<>();

    @OneToMany(mappedBy = "player")
    private List<RankPvp> rankPvpList = new ArrayList<>();

    @OneToMany(mappedBy ="player")
    private List<CasualPvp> casualPvpList = new ArrayList<>();

    @NotNull
    private int week;

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime modifiedTime;

    public Player() { }

    @Builder
    public Player(String platform, String userId, String profileId) {
        this.platform = platform;
        this.userId = userId;
        this.profileId = profileId;
        this.week = 0;
    }

    public void updateUserId(String userId) {
        this.userId = userId;
    }

    public void increaseWeek() {
        this.week++;
    }
}
