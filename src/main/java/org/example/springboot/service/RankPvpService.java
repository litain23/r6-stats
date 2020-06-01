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

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RankPvpService {
    private final RankPvpRepository rankPvpRepository;
    private final PlayerService playerService;
    private final UbiApi ubiApi;

    public RankPvpResponseDto getRankPvp(String platform, String id) {
        RankPvpDto rankPvpDto = ubiApi.getRankPvp(platform, id);
        return new RankPvpResponseDto(rankPvpDto);
    }

    @Transactional
    public void saveRankPvp(String platform, String id) {
        RankPvpDto rankPvpDto = ubiApi.getRankPvp(platform, id);
        Player player = playerService.findPlayerIfNotExistReturnNewEntity(platform, id);
        RankPvp rankPvp = new RankPvp(rankPvpDto, player);
        rankPvpRepository.save(rankPvp);
        player.getRankPvpList().add(rankPvp);
    }

    @Transactional
    public List<RankPvpResponseDto> getRankPvpAll(String platform, String id) {
        Player player = playerService.findPlayerIfNotExistReturnNewEntity(platform, id);
        List<RankPvpResponseDto> ret = player.getRankPvpList().stream()
                .map(RankPvpResponseDto::new)
                .sorted(Comparator.comparing(RankPvpResponseDto::getCreatedTime).reversed())
                .collect(Collectors.toList());

        RankPvpResponseDto recentPvp = getRankPvp(platform, id);
        recentPvp.setCreatedTime(LocalDateTime.now());
        ret.add(0, recentPvp);
        return ret;
    }
}
