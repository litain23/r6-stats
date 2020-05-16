package org.example.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.player.PlayerRepository;
import org.example.springboot.domain.rankstat.RankStatRepository;
import org.example.springboot.r6api.UbiApi;
import org.example.springboot.r6api.dto.RankStatDto;
import org.example.springboot.web.dto.RankStatResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RankStatService {
    private static final int CURRENT_SEASON = -1;

    private final RankStatRepository rankRepository;
    private final PlayerRepository playerRepository;
    private final UbiApi ubiApi;

    @Transactional
    public RankStatResponseDto getRankStat(String platform, String id, int season) {
        RankStatDto rankStatDto = ubiApi.getRankStat(platform, id, season);
        return new RankStatResponseDto(rankStatDto);
    }

    @Transactional
    public List<RankStatResponseDto> getRankStatAllSeason(String platform, String id) {
        return null;
//        Player player = playerRepository.getPlayerIfNotExistReturnNewEntity(platform, id);
//
//        RankStat currentRankStat = parseResponseStr(ubiApi.getRankStat(platform, id, CURRENT_SEASON));
//        int currentSeason = currentRankStat.getSeason();
//
//        List<RankStat> playerRankList = player.getRankList();
//        if(playerRankList.isEmpty()) {
//            for(int season = 1; season <= currentSeason; season++) {
//                RankStat rankstat = parseResponseStr(ubiApi.getRankStat(platform, id, season));
//                if(rankstat.getMaxMmr() == 0 && season != currentSeason) {
//                    continue;
//                }
//
//                rankstat.setPlayer(player);
//                rankRepository.save(rankstat);
//                playerRankList.add(rankstat);
//            }
//        }
//
//        RankStat lastRankStat = playerRankList.get(playerRankList.size() - 1);
//        // 플레이어의 마지막 랭크 시즌이 현재 시즌과 같으면 업데이트, 아니면 Add
//        if(lastRankStat.getSeason() == currentSeason) {
//            lastRankStat.updateRankStat(currentRankStat);
//        } else if(lastRankStat.getSeason() < currentSeason){
//            if(lastRankStat.getMaxMmr() == 0) {
//                // add 할때 이전시즌 또한 언랭크이면 삭제
//                int lastRankStatIndex = playerRankList.indexOf(lastRankStat);
//                playerRankList.remove(lastRankStatIndex);
//                rankRepository.delete(lastRankStat);
//            }
//
//            lastRankStat.setPlayer(player);
//            rankRepository.save(currentRankStat);
//            playerRankList.add(currentRankStat);
//        }
//
//        return playerRankList.stream()
//                    .map(RankStatResponseDto::new)
//                    .collect(Collectors.toList());
    }
}
