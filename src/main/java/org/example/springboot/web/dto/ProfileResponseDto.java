package org.example.springboot.web.dto;

import lombok.Getter;

@Getter
public class ProfileResponseDto {
    String profileId;

    public ProfileResponseDto(String profileId) {
        this.profileId = profileId;
    }
}
