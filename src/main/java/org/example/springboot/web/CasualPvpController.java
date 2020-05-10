package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.service.CasualPvpService;
import org.example.springboot.web.dto.CasualPvpResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CasualPvpController {
    private final CasualPvpService casualPvpService;

    @GetMapping("/api/v1/casualpvp/{platform}/{id}/all")
    public List<CasualPvpResponseDto> getCasualPvpAll(@PathVariable String platform,
                                                      @PathVariable String id) {

        return casualPvpService.getCasualPvpAll(platform, id);
    }

    @GetMapping("/api/v1/casualpvp/{platform}/{id}")
    public CasualPvpResponseDto getCasualPvp(@PathVariable String platform,
                                             @PathVariable String id) {

        return casualPvpService.getCasualPvp(platform, id);
    }
}
