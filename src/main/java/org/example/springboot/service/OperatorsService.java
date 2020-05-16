package org.example.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.operator.Operator;
import org.example.springboot.domain.operator.OperatorRepository;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.r6api.OperatorIndex;
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
public class OperatorsService {
    private final OperatorRepository operatorRepository;
    private final PlayerRepository playerRepository;
    private final UbiApi ubiApi;

    @Transactional
    public List<OperatorListResponseDto> getOperatorStatList(String platform, String id) {
        return getOperatorStatList(platform, id, false);
    }


    @Transactional
    public List<OperatorListResponseDto> getOperatorStatList(String platform, String id, boolean isSave) {
        List<OperatorDto> operatorDtoList = ubiApi.getOperatorsStat(platform, id);

        if(isSave) {
            Player player = playerRepository.getPlayerIfNotExistReturnNewEntity(platform, id);
            List<Operator> playerOperatorList = player.getOperatorList();
            for(OperatorDto op : operatorDtoList) {
                Operator operator = new Operator(op, player);
                operatorRepository.save(operator);
                playerOperatorList.add(operator);
            }
        }

        return operatorDtoList.stream()
                .map(OperatorListResponseDto::new)
                .sorted(Comparator.comparing(OperatorListResponseDto::getCreatedTime).reversed())
                .limit(OperatorIndex.indexList.size())
                .collect(Collectors.toList());
    }
}
