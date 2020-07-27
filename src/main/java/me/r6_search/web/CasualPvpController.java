package me.r6_search.web;

import lombok.RequiredArgsConstructor;
import me.r6_search.config.PlayerAnnotation;
import me.r6_search.service.CasualPvpService;
import me.r6_search.web.dto.r6api.CasualPvpResponseDto;
import me.r6_search.model.player.Player;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/stat")
@RestController
public class CasualPvpController {
    private final CasualPvpService casualPvpService;

    @GetMapping("/casualpvp/{platform}/{id}")
    public CasualPvpResponseDto getCasualPvp(@PlayerAnnotation Player player) {
        return new CasualPvpResponseDto(casualPvpService.getCasualPvp(player));
    }


    @GetMapping("/casualpvp/{platform}/{id}/all")
    public List<CasualPvpResponseDto> getCasualPvpAll(@PlayerAnnotation Player player) {

        return casualPvpService.getCasualPvpAll(player).stream()
                .map(CasualPvpResponseDto::new)
                .collect(Collectors.toList());
    }

}
