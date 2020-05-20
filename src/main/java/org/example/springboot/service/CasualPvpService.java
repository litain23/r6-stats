package org.example.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.casualpvp.CasualPvp;
import org.example.springboot.domain.casualpvp.CasualPvpRepository;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.dto.CasualPvpDto;
import org.example.springboot.web.dto.CasualPvpResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CasualPvpService {
    private final CasualPvpRepository casualPvpRepository;
    private final PlayerRepository playerRepository;
    private final UbiApi ubiApi;

    public CasualPvpResponseDto getCasualPvp(String platform, String id) {
        CasualPvpDto casualPvpDto = ubiApi.getCasualPvp(platform, id);
        return new CasualPvpResponseDto(casualPvpDto);
    }

    @Transactional
    public void saveCasualPvp(String platform, String id) {
        CasualPvpDto casualPvpDto = ubiApi.getCasualPvp(platform, id);
        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity(platform, id);
        CasualPvp casualPvp = new CasualPvp(casualPvpDto, player);
        casualPvpRepository.save(casualPvp);
        player.getCasualPvpList().add(casualPvp);
    }

    @Transactional
    public List<CasualPvpResponseDto> getCasualPvpAll(String platform, String id) {
        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity(platform, id);

        List<CasualPvpResponseDto> ret =  player.getCasualPvpList().stream()
                .map(CasualPvpResponseDto::new)
                .sorted(Comparator.comparing(CasualPvpResponseDto::getCreatedTime).reversed())
                .collect(Collectors.toList());
        CasualPvpResponseDto recentPvp = getCasualPvp(platform, id);
        ret.add(0, recentPvp);
        return ret;
    }
}
