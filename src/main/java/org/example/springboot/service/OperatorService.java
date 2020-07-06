package org.example.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.operator.Operator;
import org.example.springboot.domain.operator.OperatorRepository;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.seasonoperator.SeasonOperatorRepository;
import org.example.springboot.domain.weeklyoperator.WeeklyOperator;
import org.example.springboot.domain.weeklyoperator.WeeklyOperatorRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.dto.OperatorDto;
import org.example.springboot.web.dto.OperatorResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OperatorService {
    private final PlayerService playerService;
    private final SeasonOperatorRepository seasonOperatorRepository;
    private final UbiApi ubiApi;

    public List<OperatorDto> getOperatorStatList(String platform, String id, int season) {
        List<OperatorDto> operatorDtoList = ubiApi.getOperatorsStat(platform, id);

        if(season == -1) {
            return operatorDtoList;
        } else {
            Player player = playerService.findPlayerIfNotExistReturnNewEntity(platform, id);
            List<OperatorDto> seasonOperatorDto = seasonOperatorRepository.findByPlayerAnAndSeason(player, season)
                    .stream()
                    .map(OperatorDto::new)
                    .sorted(Comparator.comparing(OperatorDto::getName))
                    .collect(Collectors.toList());

            if(season == ubiApi.currentSeason) {
                return getDiffOperatorStat(seasonOperatorDto, operatorDtoList);
            } else if(season < ubiApi.currentSeason){
                return seasonOperatorDto;
            } else {
                return null;
            }
        }
    }

    private List<OperatorDto> getDiffOperatorStat(List<OperatorDto> oldOperatorDtoList, List<OperatorDto> newOperatorDtoList) {
        Map<String, OperatorDto> newOperatorDtoMap = newOperatorDtoList.stream()
                .collect(Collectors.toMap(OperatorDto::getOperatorIndex, operatorDto -> operatorDto));

        Map<String, OperatorDto> oldOperatorMap = oldOperatorDtoList.stream()
                .collect(Collectors.toMap(OperatorDto::getOperatorIndex, operatorDto -> operatorDto));

        List<OperatorDto> ret = new ArrayList<>();
        OperatorDto emptyDto = OperatorDto.builder()
                .death(0) .headShot(0) .kills(0) .meleeKills(0)
                .roundLost(0) .roundWon(0) .timePlayed(0) .totalXp(0) .build();
        
        for(String index : newOperatorDtoMap.keySet()) {
            OperatorDto newOp = newOperatorDtoMap.get(index);
            OperatorDto oldOp = oldOperatorMap.getOrDefault(index, emptyDto);

            OperatorDto diffDto = OperatorDto.builder()
                    .death(newOp.getDeath() - oldOp.getDeath())
                    .headShot(newOp.getHeadShot() - oldOp.getHeadShot())
                    .kills(newOp.getKills() - oldOp.getKills())
                    .meleeKills(newOp.getMeleeKills() - oldOp.getMeleeKills())
                    .roundLost(newOp.getRoundLost() - oldOp.getRoundLost())
                    .roundWon(newOp.getRoundWon() - oldOp.getRoundWon())
                    .timePlayed(newOp.getTimePlayed() - oldOp.getTimePlayed())
                    .totalXp(newOp.getTotalXp() - oldOp.getTotalXp())
                    .category(newOp.getCategory())
                    .name(newOp.getName())
                    .operatorIndex(newOp.getOperatorIndex())
                    .build();

            ret.add(diffDto);
        }
        return ret;
   }
}
