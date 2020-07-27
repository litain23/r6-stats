package me.r6_search.service.scheduler;

import lombok.RequiredArgsConstructor;
import me.r6_search.service.RankPvpService;
import me.r6_search.model.player.Player;
import me.r6_search.model.player.PlayerRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class RankPvpUpdateScheduler {
    private final PlayerRepository playerRepository;
    private final RankPvpService rankPvpService;

    @Scheduled(cron = "0 0 0 * * *")
    public void autoUpdateRankPvp() {
        List<Player> playerList = playerRepository.findAll();
        for(Player player : playerList) {
            rankPvpService.saveRankPvp(player);
        }
    }
}
