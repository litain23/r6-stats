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

    public RankPvpDto getRankPvp(String platform, String id) {
        return ubiApi.getRankPvp(platform, id);
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
    public List<RankPvpDto> getRankPvpAll(String platform, String id) {
        Player player = playerService.findPlayerIfNotExistReturnNewEntity(platform, id);
        List<RankPvpDto> ret = player.getRankPvpList().stream()
                .map(RankPvpDto::new)
                .sorted(Comparator.comparing(RankPvpDto::getCreatedTime).reversed())
                .collect(Collectors.toList());
        RankPvpDto recentPvp = ubiApi.getRankPvp(platform, id);
        ret.add(0, recentPvp);
        return ret;
    }
}
