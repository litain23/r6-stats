package org.example.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.casualpvp.CasualPvp;
import org.example.springboot.domain.casualpvp.CasualPvpRepository;
import org.example.springboot.domain.player.Player;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.dto.CasualPvpDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CasualPvpService {
    private final CasualPvpRepository casualPvpRepository;
    private final PlayerService playerService;
    private final UbiApi ubiApi;

    public CasualPvpDto getCasualPvp(String platform, String id) {
        return ubiApi.getCasualPvp(platform, id);
    }

    @Transactional
    public void saveCasualPvp(String platform, String id) {
        Player player = playerService.findPlayerIfNotExistReturnNewEntity(platform, id);
        CasualPvpDto casualPvpDto = ubiApi.getCasualPvp(platform, id);

        CasualPvp casualPvp = new CasualPvp(casualPvpDto, player);
        casualPvpRepository.save(casualPvp);
        player.getCasualPvpList().add(casualPvp);
    }

    @Transactional
    public List<CasualPvpDto> getCasualPvpAll(String platform, String id) {
        Player player = playerService.findPlayerIfNotExistReturnNewEntity(platform, id);

        List<CasualPvpDto> ret =  player.getCasualPvpList().stream()
                .map(CasualPvpDto::new)
                .sorted(Comparator.comparing(CasualPvpDto::getCreatedTime).reversed())
                .collect(Collectors.toList());
        CasualPvpDto recentPvp = ubiApi.getCasualPvp(platform, id);
        ret.add(0, recentPvp);
        return ret;
    }
}
