package org.example.springboot.service.scheduler;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.operators.OperatorsRepository;
import org.example.springboot.domain.player.PlayerRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OperatorUpdateScheduler {
    private final PlayerRepository playerRepository;
    private final OperatorsRepository operatorsRepository;

    @Scheduled(cron = "* * * * * *")
    private void autoUpdateOperator() {
        // TODO : Operator 와 Rank Entity 를 새롭게 수정 필요
//        List<Player> playerList = playerRepository.findAll();
//        for(Player player : playerList) {
//            String platform = player.getPlatform();
//            String playerId = player.getPlayerId();
//
//            updateOperator(platform, playerId);
//        }
    }

    private void updateOperator(String platform, String id) {

    }
}
