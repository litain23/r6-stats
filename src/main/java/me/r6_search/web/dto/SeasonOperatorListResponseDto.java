package me.r6_search.web.dto;

import java.util.List;

public class SeasonOperatorListResponseDto {
    int season;
    List<OperatorResponseDto> operators;

    public SeasonOperatorListResponseDto(int season, List<OperatorResponseDto> operators) {
        this.season = season;
        this.operators = operators;
    }
}
