package org.example.springboot.service.operators;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.operatorindex.OperatorIndex;
import org.example.springboot.domain.operatorindex.OperatorIndexRepository;
import org.example.springboot.domain.operators.Operators;
import org.example.springboot.domain.operators.OperatorsRepository;
import org.example.springboot.domain.user.User;
import org.example.springboot.domain.user.UserRepository;
import org.example.springboot.r6api.API;
import org.example.springboot.r6api.AuthToken;
import org.example.springboot.r6api.UbiAuthApi;
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
    private final UserRepository userRepository;
    private AuthToken token;

    @Transactional
    public List<OperatorListResponseDto> getRankStat(String platform, String id) {
        token = UbiAuthApi.getAuthToken();
        API api = new API(token);

        User user = userRepository.findByPlatformAndAndUserId(platform, id);
        if(user == null) {
            user = userRepository.save(
                    User.builder()
                            .platform(platform)
                            .userId(id)
                            .build()
            );
        }

        List<Operators> operatorsList = parseResponseStr(api.getOperatorsStat(platform, id));

        for(Operators op : operatorsList) {
            operatorsRepository.save(op);
            user.getOperatorsList().add(op);
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
                    .uniqueStatisticName(operatorIndex.getStatisticPvpId())
                    .uniqueStatisticOasisId(operatorIndex.getStatisticPvpOasisId())
                    .build();

            operatorsList.add(op);
        }

        return operatorsList;
    }
}
