package org.example.springboot.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.operatorindex.OperatorIndex;
import org.example.springboot.domain.operatorindex.OperatorIndexRepository;
import org.example.springboot.domain.operators.Operators;
import org.example.springboot.domain.operators.OperatorsRepository;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.web.dto.OperatorListResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OperatorsService {
    private final OperatorIndexRepository operatorIndexRepository;
    private final OperatorsRepository operatorsRepository;
    private final PlayerRepository playerRepository;
    private final UbiApi ubiApi;

    @Transactional
    public List<OperatorListResponseDto> getOperatorStatList(String platform, String id) {
        return getOperatorStatList(platform, id, false);
    }

    public List<OperatorListResponseDto> getOperatorStatList(String platform, String id, boolean isSave) {
        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity(platform, id);

        List<Operators> operatorsList = parseResponseStr(ubiApi.getOperatorsStat(platform, id));

        if(isSave) {
            List<Operators> playerOperatorList = player.getOperatorsList();
            for(Operators op : operatorsList) {
                op.setPlayer(player);
                operatorsRepository.save(op);
                playerOperatorList.add(op);
            }
        }

        return operatorsList.stream()
                .map(OperatorListResponseDto::new)
                .sorted(Comparator.comparing(OperatorListResponseDto::getOperatorIndex))
                .collect(Collectors.toList());
    }

    private List<Operators> parseResponseStr(String operatorStr) {
        Map<String, Double> operatorMap = new Gson().fromJson(operatorStr, Map.class);
        Map<String, Map<String, Double>> groupByIndexMap = operatorMap.keySet()
                .stream()
                .collect(Collectors.groupingBy(key -> {
                    String[] splitKey = key.split(":");
                    String index = splitKey[1] + ":" + splitKey[2];
                    return index;
                }, Collectors.toMap(key -> {
                    String prefix = "operatorpvp_";
                    String changeKey = key.substring(prefix.length());
                    return changeKey.split(":")[0];
                }, key -> operatorMap.get(key))));

        List<Operators> operatorsList = new ArrayList<>();

        List<OperatorIndex> operatorIndexList = operatorIndexRepository.findAll();
        for(OperatorIndex operatorIndex : operatorIndexList) {
            String index = operatorIndex.getOperatorIndex();
            Map<String, Double> operatorStat = groupByIndexMap.getOrDefault(index, Collections.EMPTY_MAP);

            Operators op = Operators.builder()
                    .death(operatorStat.getOrDefault("death", 0.0).intValue())
                    .roundLost(operatorStat.getOrDefault("roundlost", 0.0).intValue())
                    .roundWon(operatorStat.getOrDefault("roundwon", 0.0).intValue())
                    .timePlayed(operatorStat.getOrDefault("timeplayed", 0.0).intValue())
                    .totalXp(operatorStat.getOrDefault("totalxp", 0.0).intValue())
                    .meleeKills(operatorStat.getOrDefault("meleekills", 0.0).intValue())
                    .headShot(operatorStat.getOrDefault("headshot", 0.0).intValue())
                    .kills(operatorStat.getOrDefault("kills", 0.0).intValue())
                    .operatorIndex(index)
                    .category(operatorIndex.getCategory())
                    .name(operatorIndex.getName())
                    .build();

            operatorsList.add(op);
        }

        return operatorsList;
    }
}
