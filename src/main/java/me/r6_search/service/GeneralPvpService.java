package me.r6_search.service;

import lombok.RequiredArgsConstructor;
import me.r6_search.r6api.UbiApi;
import me.r6_search.r6api.dto.GeneralPvpDto;
import me.r6_search.model.player.Player;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GeneralPvpService {
    private final UbiApi ubiApi;

    public GeneralPvpDto getGeneralPvp(Player player) {
        return ubiApi.getGeneralPvp(player.getPlatform(), player.getProfileId());
    }
}
