package org.example.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.player.Player;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.dto.GeneralPvpDto;
import org.example.springboot.web.dto.GeneralPvpResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GeneralPvpService {
    private final UbiApi ubiApi;

    public GeneralPvpDto getGeneralPvp(Player player) {
        return ubiApi.getGeneralPvp(player.getPlatform(), player.getProfileId());
    }
}
