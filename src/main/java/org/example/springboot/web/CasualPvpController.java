package org.example.springboot.web;

import lombok.RequiredArgsConstructor;
import org.example.springboot.service.CasualPvpService;
import org.example.springboot.web.dto.CasualPvpResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CasualPvpController {
    private final CasualPvpService casualPvpService;

    @GetMapping("/api/v1/casualpvp/{platform}/{id}")
    public ResponseEntity<CasualPvpResponseDto> getCasualPvp(@PathVariable String platform,
                                 @PathVariable String id) {
        return ResponseEntity.ok(casualPvpService.getCasualPvp(platform, id));
    }


    @GetMapping("/api/v1/casualpvp/{platform}/{id}/all")
    public ResponseEntity<List<CasualPvpResponseDto>> getCasualPvpAll(@PathVariable String platform,
                                                      @PathVariable String id) {

        return ResponseEntity.ok(casualPvpService.getCasualPvpAll(platform, id));
    }

}
