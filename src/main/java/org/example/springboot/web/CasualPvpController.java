package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.config.PlayerAnnotation;
import org.example.springboot.domain.player.Player;
import org.example.springboot.service.CasualPvpService;
import org.example.springboot.web.dto.CasualPvpResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
