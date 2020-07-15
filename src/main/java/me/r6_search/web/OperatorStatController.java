package me.r6_search.web;

import lombok.RequiredArgsConstructor;
import me.r6_search.config.PlayerAnnotation;
import me.r6_search.domain.player.Player;
import me.r6_search.r6api.dto.OperatorDto;
import me.r6_search.service.OperatorService;
import me.r6_search.web.dto.OperatorResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
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
