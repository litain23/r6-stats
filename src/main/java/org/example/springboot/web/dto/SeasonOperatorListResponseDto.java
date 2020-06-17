package org.example.springboot.web.dto;

import java.util.List;

public class SeasonOperatorListResponseDto {
    int season;
    List<OperatorListResponseDto> operators;

    public SeasonOperatorListResponseDto(int season, List<OperatorListResponseDto> operators) {
        this.season = season;
        this.operators = operators;
    }
}
