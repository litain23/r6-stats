package me.r6_search.web.dto;

import lombok.Getter;

@Getter
public class ProfileResponseDto {
    String profileId;

    public ProfileResponseDto(String profileId) {
        this.profileId = profileId;
    }
}
