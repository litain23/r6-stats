package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.service.OperatorService;
import org.example.springboot.web.dto.OperatorListResponseDto;
import org.graalvm.compiler.replacements.nodes.ArrayEqualsNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OperatorStatApiController {
    private final OperatorService operatorService;

    @GetMapping("/api/v1/operator/{platform}/{id}")
    public List<OperatorListResponseDto> findById(@PathVariable String platform,
                                                  @PathVariable String id,
                                                  @RequestParam(defaultValue = "-1", required = false) int season) {
        return operatorService.getOperatorStatList(platform, id, season);
    }
}
