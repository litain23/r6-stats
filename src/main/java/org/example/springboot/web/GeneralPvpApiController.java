package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.service.operators.GeneralPvpService;
import org.example.springboot.web.dto.GeneralPvpResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GeneralPvpApiController {
    private final GeneralPvpService generalPvpService;

    @GetMapping("/api/v1/general/{platform}/{id}")
    public GeneralPvpResponseDto findById(@PathVariable String platform,
                                          @PathVariable String id) {

        return generalPvpService.getGeneralPvp(platform, id);
    }
}
