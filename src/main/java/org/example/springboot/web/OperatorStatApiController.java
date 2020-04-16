package org.example.springboot.web;

import org.example.springboot.r6api.*;
import org.example.springboot.web.dto.GeneralResponseDto;
import org.example.springboot.web.dto.OperatorListResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OperatorStatApiController {

    @GetMapping("/api/v1/operator/{platform}/{id}")
    public List<OperatorListResponseDto> findById(@PathVariable String platform,
                                       @PathVariable String id) {
        AuthToken token = UbiAuthApi.getAuthToken();
        API api = new API(token);

        List<Operators> operatorsList = api.getOperatorsStat(platform, id);
        List<OperatorListResponseDto> dto =
                operatorsList.stream()
                        .map(OperatorListResponseDto::new)
                        .collect(Collectors.toList());

        return dto;
    }
}
