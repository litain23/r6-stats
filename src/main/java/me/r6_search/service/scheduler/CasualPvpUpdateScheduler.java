package me.r6_search.service.scheduler;

import lombok.RequiredArgsConstructor;
import me.r6_search.service.CasualPvpService;
import me.r6_search.domain.player.Player;
import me.r6_search.domain.player.PlayerRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CasualPvpUpdateScheduler {
    private final PlayerRepository playerRepository;
    private final CasualPvpService casualPvpService;

    @Scheduled(cron = "0 0 0 * * *")
    public void autoUpdateCasualPvp() {
        List<Player> playerList = playerRepository.findAll();
        for(Player player : playerList) {
            casualPvpService.saveCasualPvp(player);
        }
    }
}