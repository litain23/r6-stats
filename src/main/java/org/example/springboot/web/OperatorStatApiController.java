package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.service.operators.OperatorsService;
import org.example.springboot.web.dto.OperatorListResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OperatorStatApiController {
    private final OperatorsService operatorsService;

    @GetMapping("/api/v1/operator/{platform}/{id}")
    public List<OperatorListResponseDto> findById(@PathVariable String platform,
                                       @PathVariable String id) {
        return operatorsService.getRankStat(platform, id);
    }
}
