package org.example.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.web.dto.GeneralPvpResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GeneralPvpService {
    private final UbiApi ubiApi;
    private final PlayerService playerService;

    @Transactional
    public GeneralPvpResponseDto getGeneralPvp(String platform, String id) {
        playerService.findPlayerIfNotExistReturnNewEntity(platform, id);
        return new GeneralPvpResponseDto(ubiApi.getGeneralPvp(platform, id));
    }
}
