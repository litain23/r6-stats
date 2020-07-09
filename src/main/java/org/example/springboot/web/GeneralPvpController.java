package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.config.PlayerAnnotation;
import org.example.springboot.domain.player.Player;
import org.example.springboot.service.GeneralPvpService;
import org.example.springboot.web.dto.GeneralPvpResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GeneralPvpController {
    private final GeneralPvpService generalPvpService;

    @GetMapping("/api/v1/generalpvp/{platform}/{id}")
    public GeneralPvpResponseDto findById(@PlayerAnnotation Player player) {
        return new GeneralPvpResponseDto(generalPvpService.getGeneralPvp(player));
    }
}
