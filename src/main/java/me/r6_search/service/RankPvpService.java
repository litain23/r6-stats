package me.r6_search.service;

import lombok.RequiredArgsConstructor;
import me.r6_search.model.rankpvp.RankPvp;
import me.r6_search.r6api.UbiApi;
import me.r6_search.model.player.Player;
import me.r6_search.model.rankpvp.RankPvpRepository;
import me.r6_search.r6api.dto.RankPvpDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RankPvpService {
    private final RankPvpRepository rankPvpRepository;
    private final UbiApi ubiApi;

    public RankPvpDto getRankPvp(Player player) {
        return ubiApi.getRankPvp(player.getPlatform(), player.getProfileId());
    }

    @Transactional
    public void saveRankPvp(Player player) {
        RankPvpDto rankPvpDto = ubiApi.getRankPvp(player.getPlatform(), player.getProfileId());
        RankPvp rankPvp = new RankPvp(rankPvpDto, player);
        rankPvpRepository.save(rankPvp);
        player.getRankPvpList().add(rankPvp);
    }

    @Transactional
    public List<RankPvpDto> getRankPvpAll(Player player) {
        List<RankPvpDto> ret = player.getRankPvpList().stream()
                .map(RankPvpDto::new)
                .sorted(Comparator.comparing(RankPvpDto::getCreatedTime).reversed())
                .collect(Collectors.toList());
        RankPvpDto recentPvp = ubiApi.getRankPvp(player.getPlatform(), player.getProfileId());
        ret.add(0, recentPvp);
        return ret;
    }
}
