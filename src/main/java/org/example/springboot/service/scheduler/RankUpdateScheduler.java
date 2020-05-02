package org.example.springboot.service.scheduler;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.service.RankStatService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RankUpdateScheduler {
    private final PlayerRepository playerRepository;
    private final RankStatService rankStatService;

    @Scheduled(cron = "* * * * * *")
    public void autoUpdateRankStat() {
//        List<Player> playerList = playerRepository.findAll();
//        for(Player player : playerList) {
//            String platform = player.getPlatform();
//            String playerId = player.getPlayerId();
//
//            rankStatService.getRankStat(platform, playerId, -1);
//        }
    }

}
