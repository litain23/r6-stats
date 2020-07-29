package me.r6_search.service;

import lombok.RequiredArgsConstructor;
import me.r6_search.model.casualpvp.CasualPvp;
import me.r6_search.model.casualpvp.CasualPvpRepository;
import me.r6_search.r6api.UbiApi;
import me.r6_search.model.player.Player;
import me.r6_search.r6api.dto.CasualPvpDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CasualPvpService {
    private final CasualPvpRepository casualPvpRepository;
    private final UbiApi ubiApi;

    public CasualPvpDto getCasualPvp(Player player) {
        return ubiApi.getCasualPvp(player.getPlatform(), player.getProfileId());
    }

    @Transactional
    public void saveCasualPvp(Player player) {
        CasualPvpDto casualPvpDto = ubiApi.getCasualPvp(player.getPlatform(), player.getProfileId());

        CasualPvp casualPvp = new CasualPvp(casualPvpDto, player);
        casualPvpRepository.save(casualPvp);
        player.getCasualPvpList().add(casualPvp);
    }

    @Transactional
    public List<CasualPvpDto> getCasualPvpAll(Player player) {
        List<CasualPvpDto> ret =  player.getCasualPvpList().stream()
                .map(CasualPvpDto::new)
                .sorted(Comparator.comparing(CasualPvpDto::getCreatedTime).reversed())
                .collect(Collectors.toList());
        CasualPvpDto recentPvp = ubiApi.getCasualPvp(player.getPlatform(), player.getProfileId());
        ret.add(0, recentPvp);
        return ret;
    }
}
