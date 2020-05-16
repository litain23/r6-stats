package org.example.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.domain.rankpvp.RankPvp;
import org.example.springboot.domain.rankpvp.RankPvpRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.dto.RankPvpDto;
import org.example.springboot.web.dto.RankPvpResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RankPvpService {
    private final RankPvpRepository rankPvpRepository;
    private final PlayerRepository playerRepository;
    private final UbiApi ubiApi;

    @Transactional
    public RankPvpResponseDto getRankPvp(String platform, String id) {
        return getRankPvp(platform, id, false);
    }

    @Transactional
    public RankPvpResponseDto getRankPvp(String platform, String id, boolean isSave) {
        RankPvpDto rankPvpDto = ubiApi.getRankPvp(platform, id);

        if(isSave) {
            Player player = playerRepository.getPlayerIfNotExistReturnNewEntity(platform, id);
            RankPvp rankPvp = new RankPvp(rankPvpDto, player);
            rankPvpRepository.save(rankPvp);
            player.getRankPvpList().add(rankPvp);
        }

        return new RankPvpResponseDto(rankPvpDto);
    }

    @Transactional
    public List<RankPvpResponseDto> getRankPvpAll(String platform, String id) {
        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity(platform, id);
        List<RankPvpResponseDto> ret = player.getRankPvpList().stream()
                .map(RankPvpResponseDto::new)
                .sorted(Comparator.comparing(RankPvpResponseDto::getCreatedTime).reversed())
                .collect(Collectors.toList());

        RankPvpResponseDto recentPvp = getRankPvp(platform, id);
        ret.add(0, recentPvp);
        return ret;
    }
}
