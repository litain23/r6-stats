package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.config.PlayerAnnotation;
import org.example.springboot.domain.player.Player;
import org.example.springboot.r6api.dto.OperatorDto;
import org.example.springboot.service.OperatorService;
import org.example.springboot.web.dto.OperatorResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class OperatorStatController {
    private final OperatorService operatorService;

    @GetMapping("/api/v1/operator/{platform}/{id}")
    public List<OperatorResponseDto> findById(@PlayerAnnotation Player player,
                                              @RequestParam(defaultValue = "-1", required = false) int season) {
        List<OperatorDto> ret = new ArrayList<>();
        if(season == -1) {
            ret = operatorService.getTotalOperatorStatList(player);
        } else {
            ret = operatorService.getSeasonOperatorStatList(player, season);
        }
        return ret.stream()
                .map(OperatorResponseDto::new)
                .collect(Collectors.toList());
    }
}
