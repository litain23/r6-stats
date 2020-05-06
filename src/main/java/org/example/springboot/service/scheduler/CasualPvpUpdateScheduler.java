package org.example.springboot.service.scheduler;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.service.CasualPvpService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CasualPvpUpdateScheduler {
    private final PlayerRepository playerRepository;
    private final CasualPvpService casualPvpService;

    @Scheduled(cron = "* * 0 * * *")
    public void autoUpdateCasualPvp() {
        List<Player> playerList = playerRepository.findAll();
        for(Player player : playerList) {
            casualPvpService.getCasualPvp(player.getPlatform(), player.getPlayerId());
        }
    }
}