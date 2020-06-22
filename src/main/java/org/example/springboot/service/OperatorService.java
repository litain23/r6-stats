package org.example.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.operator.Operator;
import org.example.springboot.domain.operator.OperatorRepository;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.domain.seasonoperator.SeasonOperatorRepository;
import org.example.springboot.domain.weeklyoperator.WeeklyOperator;
import org.example.springboot.domain.weeklyoperator.WeeklyOperatorRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.dto.OperatorDto;
import org.example.springboot.web.dto.OperatorListResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OperatorService {
    private final OperatorRepository operatorRepository;
    private final PlayerService playerService;
    private final WeeklyOperatorRepository weeklyOperatorRepository;
    private final SeasonOperatorRepository seasonOperatorRepository;
    private final UbiApi ubiApi;

    @Transactional
    public List<OperatorListResponseDto> getOperatorStatList(String platform, String id, int season) {
        List<OperatorDto> operatorDtoList = ubiApi.getOperatorsStat(platform, id);
        return operatorDtoList.stream()
                .map(OperatorListResponseDto::new)
                .sorted(Comparator.comparing(OperatorListResponseDto::getCreatedTime).reversed())
                .collect(Collectors.toList());
    }


    @Transactional
    public void saveOperatorStat(String platform, String id){
        List<OperatorDto> operatorDtoList = ubiApi.getOperatorsStat(platform, id);
        Player player = playerService.findPlayerIfNotExistReturnNewEntity(platform, id);

        WeeklyOperator weeklyOperator = WeeklyOperator.builder()
                .player(player)
                .week(player.getWeek())
                .build();

        player.increaseWeek();

        weeklyOperatorRepository.save(weeklyOperator);

        for(OperatorDto op : operatorDtoList) {
            Operator operator = new Operator(op);
            operatorRepository.save(operator);
            weeklyOperator.addOperator(operator);
        }

        player.getWeeklyOperatorList().add(weeklyOperator);
    }
}
