package org.example.springboot.web.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankStatRequestDto {
    String platform;
    String id;
    String region;

    public RankStatRequestDto(String platform, String id) {
        this.platform = platform;
        this.id = id;
    }
}
