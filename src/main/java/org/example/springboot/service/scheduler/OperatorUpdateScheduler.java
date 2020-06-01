package org.example.springboot.service.scheduler;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.service.OperatorService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class OperatorUpdateScheduler {
    private final PlayerRepository playerRepository;
    private final OperatorService operatorService;

    @Scheduled(cron = "0 0 0 * * *")
    private void autoUpdateOperator() {
        UbiApi.week++;

        List<Player> playerList = playerRepository.findAll();
        for(Player player : playerList) {
            operatorService.saveOperatorStat(player.getPlatform(), player.getUserId());
        }
    }
}
