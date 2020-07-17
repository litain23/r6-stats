package me.r6_search.web;

import lombok.RequiredArgsConstructor;
import me.r6_search.config.PlayerAnnotation;
import me.r6_search.web.dto.r6api.RankStatSeasonResponseDto;
import me.r6_search.domain.player.Player;
import me.r6_search.r6api.dto.RankStatDto;
import me.r6_search.service.RankStatService;
import me.r6_search.web.dto.r6api.RankStatRegionResponseDto;
import me.r6_search.web.dto.r6api.RankStatResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class RankStatController {
    private final RankStatService rankStatService;

    @GetMapping("/api/v1/rank/{platform}/{id}/all")
    public List<RankStatSeasonResponseDto> getRankStatAllSeason(@PlayerAnnotation Player player) {
        List<RankStatDto> rankStatDtoList = rankStatService.getRankStatAllSeason(player);

        return convertStatDtoToSeasonDto(
                rankStatDtoList.stream()
                    .map(RankStatResponseDto::new)
                    .collect(Collectors.toList())
        );
    }

    @GetMapping("/api/v1/rank/{platform}/{id}")
    public List<RankStatRegionResponseDto> getRankStat(@PlayerAnnotation Player player,
                                                       @RequestParam(defaultValue = "-1", required = false) int season) {
        List<RankStatDto> rankStatDtoList = rankStatService.getRankStat(player, season);
        return convertStatDtoToRegionDto(
                rankStatDtoList.stream()
                    .map(RankStatResponseDto::new)
                    .collect(Collectors.toList())
        );
    }

    private List<RankStatSeasonResponseDto> convertStatDtoToSeasonDto(List<RankStatResponseDto> dto) {
        List<RankStatSeasonResponseDto> ret = new ArrayList<>();
        Map<Integer, List<RankStatResponseDto>> map = dto.stream().collect(Collectors.groupingBy(RankStatResponseDto::getSeason));

        for(int season : map.keySet()) {
            ret.add(new RankStatSeasonResponseDto(season, convertStatDtoToRegionDto(map.get(season))));
        }
        ret.sort(Comparator.comparing(t -> t.getSeason(), Comparator.reverseOrder()));
        return ret;
    }

    private List<RankStatRegionResponseDto> convertStatDtoToRegionDto(List<RankStatResponseDto> dto) {
        List<RankStatRegionResponseDto> ret = new ArrayList<>();
        Map<String, List<RankStatResponseDto>> map = dto.stream().collect(Collectors.groupingBy(RankStatResponseDto::getRegion));

        for(String region : map.keySet()) {
            ret.add(new RankStatRegionResponseDto(region, map.get(region).get(0)));
        }
        return ret;
    }
}
