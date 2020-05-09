package org.example.springboot.services;

import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.domain.rankstat.RankStat;
import org.example.springboot.domain.rankstat.RankStatRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RankStatServiceTest {
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    RankStatRepository rankStatRepository;

    private final String id = "R6TestId120j9cv9";

    @Test
    public void 이전시즌_언랭크일때_지워지는지_확인() {
        // 그런 아이디를 검색할 수 없어서 로직을 가져옴
        int currentSeason = 17;
        int previousSeason = 16;

        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity("uplay", id);
        RankStat previousRank = RankStat.builder()
                .rank(0)
                .maxRank(0)
                .abandons(0)
                .death(0)
                .kills(0)
                .maxMmr(0)
                .losses(0)
                .mmr(0)
                .region("apac")
                .season(previousSeason) // 이전 시즌인 16;
                .wins(0)
                .build();

        RankStat currentRankStat = RankStat.builder()
                .rank(0)
                .maxRank(0)
                .abandons(0)
                .death(0)
                .kills(0)
                .maxMmr(0)
                .losses(0)
                .mmr(0)
                .region("apac")
                .season(currentSeason) // 현재시즌이 17
                .wins(0)
                .build();

        previousRank.setPlayer(player);
        rankStatRepository.save(previousRank);
        player.getRankList().add(previousRank);

        List<RankStat> playerRankList = player.getRankList();
        RankStat lastRankStat = playerRankList.get(playerRankList.size() - 1);
        // 플레이어의 마지막 랭크 시즌이 현재 시즌과 같으면 업데이트, 아니면 Add
        if(lastRankStat.getSeason() == currentSeason) {
            lastRankStat.updateRankStat(currentRankStat);
        } else if(lastRankStat.getSeason() < currentSeason){
            if(lastRankStat.getMaxMmr() == 0) {
                // add 할때 이전시즌 또한 언랭크이면 삭제
                int lastRanksStatIndex = playerRankList.indexOf(lastRankStat);
                rankStatRepository.delete(lastRankStat);
                playerRankList.remove(lastRanksStatIndex);
            }

            lastRankStat.setPlayer(player);
            rankStatRepository.save(currentRankStat);
            playerRankList.add(currentRankStat);
        }

        assertThat(playerRankList.size()).isEqualTo(1);
        assertThat(playerRankList.get(0).getMaxMmr()).isEqualTo(0);

    }
}
