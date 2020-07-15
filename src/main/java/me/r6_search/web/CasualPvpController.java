package me.r6_search.web;

import lombok.RequiredArgsConstructor;
import me.r6_search.config.PlayerAnnotation;
import me.r6_search.service.CasualPvpService;
import me.r6_search.web.dto.CasualPvpResponseDto;
import me.r6_search.domain.player.Player;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class CasualPvpController {
    private final CasualPvpService casualPvpService;

    @GetMapping("/api/v1/casualpvp/{platform}/{id}")
    public CasualPvpResponseDto getCasualPvp(@PlayerAnnotation Player player) {
        return new CasualPvpResponseDto(casualPvpService.getCasualPvp(player));
    }


    @GetMapping("/api/v1/casualpvp/{platform}/{id}/all")
    public List<CasualPvpResponseDto> getCasualPvpAll(@PlayerAnnotation Player player) {

        return casualPvpService.getCasualPvpAll(player).stream()
                .map(CasualPvpResponseDto::new)
                .collect(Collectors.toList());
    }

}
