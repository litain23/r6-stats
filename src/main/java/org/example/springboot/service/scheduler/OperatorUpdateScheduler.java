package org.example.springboot.service.scheduler;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.service.OperatorsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class OperatorUpdateScheduler {
    private final PlayerRepository playerRepository;
    private final OperatorsService operatorsService;

    @Scheduled(cron = "0 0 0 * * *")
    private void autoUpdateOperator() {
        List<Player> playerList = playerRepository.findAll();
        for(Player player : playerList) {
            operatorsService.getOperatorStatList(player.getPlatform(), player.getUserId(), true);
        }
    }
}
