package me.r6_search.service;

import lombok.RequiredArgsConstructor;
import me.r6_search.model.seasonoperator.SeasonOperator;
import me.r6_search.model.seasonoperator.SeasonOperatorRepository;
import me.r6_search.r6api.UbiApi;
import me.r6_search.model.operator.Operator;
import me.r6_search.model.operator.OperatorRepository;
import me.r6_search.model.player.Player;
import me.r6_search.r6api.dto.OperatorDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OperatorService {
    private final SeasonOperatorRepository seasonOperatorRepository;
    private final OperatorRepository operatorRepository;
    private final UbiApi ubiApi;

    public List<OperatorDto> getSeasonOperatorStatList(Player player, int season) {
        if(ubiApi.currentSeason < season) {
            return Collections.EMPTY_LIST;
        }

        SeasonOperator seasonOperator = seasonOperatorRepository.findByPlayerAndSeason(player, season);
        if(seasonOperator == null) {
            return Collections.EMPTY_LIST;
        }

        if(season == ubiApi.currentSeason) {
            List<OperatorDto> operatorDtoList = ubiApi.getOperatorsStat(player.getPlatform(), player.getProfileId());
            return getDiffOperatorStat(entityToDto(seasonOperator.getOperatorList()), operatorDtoList);
        } else {
            return entityToDto(seasonOperator.getOperatorList());
        }
    }

    public List<OperatorDto> getTotalOperatorStatList(Player player) {
        List<OperatorDto> operatorDtoList = ubiApi.getOperatorsStat(player.getPlatform(), player.getProfileId());
        saveIfDontExistCurrentSeasonOperatorData(player, operatorDtoList);
        return operatorDtoList;
    }

    private List<OperatorDto> entityToDto(List<Operator> operatorList) {
        return operatorList.stream()
                .map(OperatorDto::new)
                .collect(Collectors.toList());
    }


    private boolean isExistCurrentSeasonOperatorData(Player player) {
        SeasonOperator seasonOperator = seasonOperatorRepository.findByPlayerAndSeason(player, ubiApi.currentSeason);
        return seasonOperator != null && seasonOperator.getOperatorList().isEmpty() != true;
    }

    private void saveIfDontExistCurrentSeasonOperatorData(Player player, List<OperatorDto> operatorDtoList) {
        if(!isExistCurrentSeasonOperatorData(player)) {
            List<Operator> operatorList = operatorDtoList.stream().map(Operator::new).collect(Collectors.toList());
            for(Operator op : operatorList) {
                operatorRepository.save(op);
            }

            SeasonOperator seasonOperator = SeasonOperator.builder()
                    .operatorList(operatorList)
                    .season(ubiApi.currentSeason)
                    .player(player)
                    .build();
            seasonOperatorRepository.save(seasonOperator);
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
