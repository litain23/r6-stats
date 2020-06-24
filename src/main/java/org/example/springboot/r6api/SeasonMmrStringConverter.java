package org.example.springboot.r6api;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.*;

public class SeasonMmrStringConverter{
    Map<Integer, List<MmrRange>> map;

    public SeasonMmrStringConverter() {
        Map<Integer, List<MmrRange>> result = new HashMap<>();
        result.put(2, Arrays.asList(new MmrRange[]{
                new MmrRange("COPPER I", 2199),
                new MmrRange("COPPER II", 2399),
                new MmrRange("COPPER III", 2549),
                new MmrRange("COPPER IV", 2699),
                new MmrRange("BRONZE I", 2799),
                new MmrRange("BRONZE II", 2899),
                new MmrRange("BRONZE III", 3049),
                new MmrRange("BRONZE IV", 3199),
                new MmrRange("SILVER I", 3349),
                new MmrRange("SILVER II", 3519),
                new MmrRange("SILVER III", 3699),
                new MmrRange("SILVER IV", 3929),
                new MmrRange("GOLD I", 4159),
                new MmrRange("GOLD II", 4399),
                new MmrRange("GOLD III", 4639),
                new MmrRange("GOLD IV", 4899),
                new MmrRange("PLATINUM I", 5159),
                new MmrRange("PLATINUM II", 5449),
                new MmrRange("PLATINUM III", 5999),
                new MmrRange("DIAMOND", 9999)
        }));
        result.put(3, Arrays.asList(new MmrRange[]{
                new MmrRange("COPPER I", 2199),
                new MmrRange("COPPER II", 2399),
                new MmrRange("COPPER III", 2549),
                new MmrRange("COPPER IV", 2699),
                new MmrRange("BRONZE I", 2799),
                new MmrRange("BRONZE II", 2899),
                new MmrRange("BRONZE III", 3049),
                new MmrRange("BRONZE IV", 3199),
                new MmrRange("SILVER I", 3349),
                new MmrRange("SILVER II", 3519),
                new MmrRange("SILVER III", 3699),
                new MmrRange("SILVER IV", 3929),
                new MmrRange("GOLD I", 4159),
                new MmrRange("GOLD II", 4399),
                new MmrRange("GOLD III", 4639),
                new MmrRange("GOLD IV", 4899),
                new MmrRange("PLATINUM I", 5159),
                new MmrRange("PLATINUM II", 5449),
                new MmrRange("PLATINUM III", 5999),
                new MmrRange("DIAMOND", 9999)
        }));
        result.put(4, Arrays.asList(new MmrRange[]{
                new MmrRange("COPPER I", 1399),
                new MmrRange("COPPER II", 1499),
                new MmrRange("COPPER III", 1599),
                new MmrRange("COPPER IV", 1699),
                new MmrRange("BRONZE I", 1799),
                new MmrRange("BRONZE II", 1899),
                new MmrRange("BRONZE III", 1999),
                new MmrRange("BRONZE IV", 2099),
                new MmrRange("SILVER I", 2199),
                new MmrRange("SILVER II", 2299),
                new MmrRange("SILVER III", 2399),
                new MmrRange("SILVER IV", 2499),
                new MmrRange("GOLD I", 2599),
                new MmrRange("GOLD II", 2699),
                new MmrRange("GOLD III", 2799),
                new MmrRange("GOLD IV", 2999),
                new MmrRange("PLATINUM I", 3199),
                new MmrRange("PLATINUM II", 3399),
                new MmrRange("PLATINUM III", 3699),
                new MmrRange("DIAMOND", 9999)
        }));
        result.put(5, Arrays.asList(new MmrRange[]{
                new MmrRange("COPPER IV", 1399),
                new MmrRange("COPPER III", 1499),
                new MmrRange("COPPER II", 1599),
                new MmrRange("COPPER I", 1699),
                new MmrRange("BRONZE IV", 1799),
                new MmrRange("BRONZE III", 1899),
                new MmrRange("BRONZE II", 1999),
                new MmrRange("BRONZE I", 2099),
                new MmrRange("SILVER IV", 2199),
                new MmrRange("SILVER III", 2299),
                new MmrRange("SILVER II", 2399),
                new MmrRange("SILVER I", 2499),
                new MmrRange("GOLD IV", 2699),
                new MmrRange("GOLD III", 2899),
                new MmrRange("GOLD II", 3099),
                new MmrRange("GOLD I", 3299),
                new MmrRange("PLATINUM III", 3699),
                new MmrRange("PLATINUM II", 4099),
                new MmrRange("PLATINUM I", 4499),
                new MmrRange("DIAMOND", 9999)
        }));
        result.put(6, Arrays.asList(new MmrRange[]{
                new MmrRange("COPPER IV", 1399),
                new MmrRange("COPPER III", 1499),
                new MmrRange("COPPER II", 1599),
                new MmrRange("COPPER I", 1699),
                new MmrRange("BRONZE IV", 1799),
                new MmrRange("BRONZE III", 1899),
                new MmrRange("BRONZE II", 1999),
                new MmrRange("BRONZE I", 2099),
                new MmrRange("SILVER IV", 2199),
                new MmrRange("SILVER III", 2299),
                new MmrRange("SILVER II", 2399),
                new MmrRange("SILVER I", 2499),
                new MmrRange("GOLD IV", 2699),
                new MmrRange("GOLD III", 2899),
                new MmrRange("GOLD II", 3099),
                new MmrRange("GOLD I", 3299),
                new MmrRange("PLATINUM III", 3699),
                new MmrRange("PLATINUM II", 4099),
                new MmrRange("PLATINUM I", 4499),
                new MmrRange("DIAMOND", 9999)
        }));
        result.put(7, Arrays.asList(new MmrRange[]{
                new MmrRange("COPPER IV", 1399),
                new MmrRange("COPPER III", 1499),
                new MmrRange("COPPER II", 1599),
                new MmrRange("COPPER I", 1699),
                new MmrRange("BRONZE IV", 1799),
                new MmrRange("BRONZE III", 1899),
                new MmrRange("BRONZE II", 1999),
                new MmrRange("BRONZE I", 2099),
                new MmrRange("SILVER IV", 2199),
                new MmrRange("SILVER III", 2299),
                new MmrRange("SILVER II", 2399),
                new MmrRange("SILVER I", 2499),
                new MmrRange("GOLD IV", 2699),
                new MmrRange("GOLD III", 2899),
                new MmrRange("GOLD II", 3099),
                new MmrRange("GOLD I", 3299),
                new MmrRange("PLATINUM III", 3699),
                new MmrRange("PLATINUM II", 4099),
                new MmrRange("PLATINUM I", 4499),
                new MmrRange("DIAMOND", 9999)
        }));
        result.put(8, Arrays.asList(new MmrRange[]{
                new MmrRange("COPPER IV", 1399),
                new MmrRange("COPPER III", 1499),
                new MmrRange("COPPER II", 1599),
                new MmrRange("COPPER I", 1699),
                new MmrRange("BRONZE IV", 1799),
                new MmrRange("BRONZE III", 1899),
                new MmrRange("BRONZE II", 1999),
                new MmrRange("BRONZE I", 2099),
                new MmrRange("SILVER IV", 2199),
                new MmrRange("SILVER III", 2299),
                new MmrRange("SILVER II", 2399),
                new MmrRange("SILVER I", 2499),
                new MmrRange("GOLD IV", 2699),
                new MmrRange("GOLD III", 2899),
                new MmrRange("GOLD II", 3099),
                new MmrRange("GOLD I", 3299),
                new MmrRange("PLATINUM III", 3699),
                new MmrRange("PLATINUM II", 4099),
                new MmrRange("PLATINUM I", 4499),
                new MmrRange("DIAMOND", 9999)
        }));
        result.put(9, Arrays.asList(new MmrRange[]{
                new MmrRange("COPPER IV", 1399),
                new MmrRange("COPPER III", 1499),
                new MmrRange("COPPER II", 1599),
                new MmrRange("COPPER I", 1699),
                new MmrRange("BRONZE IV", 1799),
                new MmrRange("BRONZE III", 1899),
                new MmrRange("BRONZE II", 1999),
                new MmrRange("BRONZE I", 2099),
                new MmrRange("SILVER IV", 2199),
                new MmrRange("SILVER III", 2299),
                new MmrRange("SILVER II", 2399),
                new MmrRange("SILVER I", 2499),
                new MmrRange("GOLD IV", 2699),
                new MmrRange("GOLD III", 2899),
                new MmrRange("GOLD II", 3099),
                new MmrRange("GOLD I", 3299),
                new MmrRange("PLATINUM III", 3699),
                new MmrRange("PLATINUM II", 4099),
                new MmrRange("PLATINUM I", 4499),
                new MmrRange("DIAMOND", 9999)
        }));
        result.put(10, Arrays.asList(new MmrRange[]{
                new MmrRange("COPPER IV", 1399),
                new MmrRange("COPPER III", 1499),
                new MmrRange("COPPER II", 1599),
                new MmrRange("COPPER I", 1699),
                new MmrRange("BRONZE IV", 1799),
                new MmrRange("BRONZE III", 1899),
                new MmrRange("BRONZE II", 1999),
                new MmrRange("BRONZE I", 2099),
                new MmrRange("SILVER IV", 2199),
                new MmrRange("SILVER III", 2299),
                new MmrRange("SILVER II", 2399),
                new MmrRange("SILVER I", 2499),
                new MmrRange("GOLD IV", 2699),
                new MmrRange("GOLD III", 2899),
                new MmrRange("GOLD II", 3099),
                new MmrRange("GOLD I", 3299),
                new MmrRange("PLATINUM III", 3699),
                new MmrRange("PLATINUM II", 4099),
                new MmrRange("PLATINUM I", 4499),
                new MmrRange("DIAMOND", 9999)
        }));
        result.put(11, Arrays.asList(new MmrRange[]{
                new MmrRange("COPPER IV", 1399),
                new MmrRange("COPPER III", 1499),
                new MmrRange("COPPER II", 1599),
                new MmrRange("COPPER I", 1699),
                new MmrRange("BRONZE IV", 1799),
                new MmrRange("BRONZE III", 1899),
                new MmrRange("BRONZE II", 1999),
                new MmrRange("BRONZE I", 2099),
                new MmrRange("SILVER IV", 2199),
                new MmrRange("SILVER III", 2299),
                new MmrRange("SILVER II", 2399),
                new MmrRange("SILVER I", 2499),
                new MmrRange("GOLD IV", 2699),
                new MmrRange("GOLD III", 2899),
                new MmrRange("GOLD II", 3099),
                new MmrRange("GOLD I", 3299),
                new MmrRange("PLATINUM III", 3699),
                new MmrRange("PLATINUM II", 4099),
                new MmrRange("PLATINUM I", 4499),
                new MmrRange("DIAMOND", 9999)
        }));
        result.put(12, Arrays.asList(new MmrRange[]{
                new MmrRange("COPPER IV", 1399),
                new MmrRange("COPPER III", 1499),
                new MmrRange("COPPER II", 1599),
                new MmrRange("COPPER I", 1699),
                new MmrRange("BRONZE IV", 1799),
                new MmrRange("BRONZE III", 1899),
                new MmrRange("BRONZE II", 1999),
                new MmrRange("BRONZE I", 2099),
                new MmrRange("SILVER IV", 2199),
                new MmrRange("SILVER III", 2299),
                new MmrRange("SILVER II", 2399),
                new MmrRange("SILVER I", 2499),
                new MmrRange("GOLD IV", 2699),
                new MmrRange("GOLD III", 2899),
                new MmrRange("GOLD II", 3099),
                new MmrRange("GOLD I", 3299),
                new MmrRange("PLATINUM III", 3699),
                new MmrRange("PLATINUM II", 4099),
                new MmrRange("PLATINUM I", 4499),
                new MmrRange("DIAMOND", 9999)
        }));
        result.put(13, Arrays.asList(new MmrRange[]{
                new MmrRange("COPPER IV", 1399),
                new MmrRange("COPPER III", 1499),
                new MmrRange("COPPER II", 1599),
                new MmrRange("COPPER I", 1699),
                new MmrRange("BRONZE IV", 1799),
                new MmrRange("BRONZE III", 1899),
                new MmrRange("BRONZE II", 1999),
                new MmrRange("BRONZE I", 2099),
                new MmrRange("SILVER IV", 2199),
                new MmrRange("SILVER III", 2299),
                new MmrRange("SILVER II", 2399),
                new MmrRange("SILVER I", 2499),
                new MmrRange("GOLD IV", 2699),
                new MmrRange("GOLD III", 2899),
                new MmrRange("GOLD II", 3099),
                new MmrRange("GOLD I", 3299),
                new MmrRange("PLATINUM III", 3699),
                new MmrRange("PLATINUM II", 4099),
                new MmrRange("PLATINUM I", 4499),
                new MmrRange("DIAMOND", 9999)
        }));
        result.put(14, Arrays.asList(new MmrRange[]{
                new MmrRange("COPPER IV", 1399),
                new MmrRange("COPPER III", 1499),
                new MmrRange("COPPER II", 1599),
                new MmrRange("COPPER I", 1699),
                new MmrRange("BRONZE IV", 1799),
                new MmrRange("BRONZE III", 1899),
                new MmrRange("BRONZE II", 1999),
                new MmrRange("BRONZE I", 2099),
                new MmrRange("SILVER IV", 2199),
                new MmrRange("SILVER III", 2299),
                new MmrRange("SILVER II", 2399),
                new MmrRange("SILVER I", 2499),
                new MmrRange("GOLD IV", 2699),
                new MmrRange("GOLD III", 2899),
                new MmrRange("GOLD II", 3099),
                new MmrRange("GOLD I", 3299),
                new MmrRange("PLATINUM III", 3699),
                new MmrRange("PLATINUM II", 4099),
                new MmrRange("PLATINUM I", 4499),
                new MmrRange("DIAMOND", 9999)
        }));
        result.put(15, Arrays.asList(new MmrRange[]{
                new MmrRange("COPPER V", 1199),
                new MmrRange("COPPER IV", 1299),
                new MmrRange("COPPER III", 1399),
                new MmrRange("COPPER II", 1499),
                new MmrRange("COPPER I", 1599),
                new MmrRange("BRONZE V", 1699),
                new MmrRange("BRONZE IV", 1799),
                new MmrRange("BRONZE III", 1899),
                new MmrRange("BRONZE II", 1999),
                new MmrRange("BRONZE I", 2099),
                new MmrRange("SILVER V", 2199),
                new MmrRange("SILVER IV", 2299),
                new MmrRange("SILVER III", 2399),
                new MmrRange("SILVER II", 2499),
                new MmrRange("SILVER I", 2599),
                new MmrRange("GOLD III", 2799),
                new MmrRange("GOLD II", 2999),
                new MmrRange("GOLD I", 3199),
                new MmrRange("PLATINUM III", 3599),
                new MmrRange("PLATINUM II", 3999),
                new MmrRange("PLATINUM I", 4399),
                new MmrRange("DIAMOND", 4999),
                new MmrRange("CHAMPIONS", 9999)
        }));
        result.put(16, Arrays.asList(new MmrRange[]{
                new MmrRange("COPPER V", 1199),
                new MmrRange("COPPER IV", 1299),
                new MmrRange("COPPER III", 1399),
                new MmrRange("COPPER II", 1499),
                new MmrRange("COPPER I", 1599),
                new MmrRange("BRONZE V", 1699),
                new MmrRange("BRONZE IV", 1799),
                new MmrRange("BRONZE III", 1899),
                new MmrRange("BRONZE II", 1999),
                new MmrRange("BRONZE I", 2099),
                new MmrRange("SILVER V", 2199),
                new MmrRange("SILVER IV", 2299),
                new MmrRange("SILVER III", 2399),
                new MmrRange("SILVER II", 2499),
                new MmrRange("SILVER I", 2599),
                new MmrRange("GOLD III", 2799),
                new MmrRange("GOLD II", 2999),
                new MmrRange("GOLD I", 3199),
                new MmrRange("PLATINUM III", 3599),
                new MmrRange("PLATINUM II", 3999),
                new MmrRange("PLATINUM I", 4399),
                new MmrRange("DIAMOND", 4999),
                new MmrRange("CHAMPIONS", 9999)
        }));
        result.put(17, Arrays.asList(new MmrRange[]{
                new MmrRange("COPPER V", 1199),
                new MmrRange("COPPER IV", 1299),
                new MmrRange("COPPER III", 1399),
                new MmrRange("COPPER II", 1499),
                new MmrRange("COPPER I", 1599),
                new MmrRange("BRONZE V", 1699),
                new MmrRange("BRONZE IV", 1799),
                new MmrRange("BRONZE III", 1899),
                new MmrRange("BRONZE II", 1999),
                new MmrRange("BRONZE I", 2099),
                new MmrRange("SILVER V", 2199),
                new MmrRange("SILVER IV", 2299),
                new MmrRange("SILVER III", 2399),
                new MmrRange("SILVER II", 2499),
                new MmrRange("SILVER I", 2599),
                new MmrRange("GOLD III", 2799),
                new MmrRange("GOLD II", 2999),
                new MmrRange("GOLD I", 3199),
                new MmrRange("PLATINUM III", 3599),
                new MmrRange("PLATINUM II", 3999),
                new MmrRange("PLATINUM I", 4399),
                new MmrRange("DIAMOND", 4999),
                new MmrRange("CHAMPIONS", 9999)
        }));
        result.put(18, Arrays.asList(new MmrRange[]{
                new MmrRange("COPPER V", 1199),
                new MmrRange("COPPER IV", 1299),
                new MmrRange("COPPER III", 1399),
                new MmrRange("COPPER II", 1499),
                new MmrRange("COPPER I", 1599),
                new MmrRange("BRONZE V", 1699),
                new MmrRange("BRONZE IV", 1799),
                new MmrRange("BRONZE III", 1899),
                new MmrRange("BRONZE II", 1999),
                new MmrRange("BRONZE I", 2099),
                new MmrRange("SILVER V", 2199),
                new MmrRange("SILVER IV", 2299),
                new MmrRange("SILVER III", 2399),
                new MmrRange("SILVER II", 2499),
                new MmrRange("SILVER I", 2599),
                new MmrRange("GOLD III", 2799),
                new MmrRange("GOLD II", 2999),
                new MmrRange("GOLD I", 3199),
                new MmrRange("PLATINUM III", 3599),
                new MmrRange("PLATINUM II", 3999),
                new MmrRange("PLATINUM I", 4399),
                new MmrRange("DIAMOND", 4999),
                new MmrRange("CHAMPIONS", 9999)
        }));
        map = result;
    }

    public String convertMmrToStringRank(int season, int mmr) {
        List<MmrRange> seasonMmrRange = map.get(season);
        for(MmrRange mmrRange : seasonMmrRange) {
            if(mmr <= mmrRange.mmrEnd) {
                return mmrRange.name;
            }
        }
        return "UNRANKED";
    }

    static class MmrRange {
        String name;
        int mmrEnd;

        public MmrRange(String name, int mmrEnd) {
            this.name = name;
            this.mmrEnd = mmrEnd;
        }
    }
}
