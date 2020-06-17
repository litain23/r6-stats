package org.example.springboot.r6api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProfileDto {
    private String profileId;
    private String userId;
    private String platformType;
    private String idOnPlatform;
    private String nameOnPlatform;

    @Builder
    public ProfileDto(String profileId, String userId, String platformType, String idOnPlatform, String nameOnPlatform) {
        this.profileId = profileId;
        this.userId = userId;
        this.platformType = platformType;
        this.idOnPlatform = idOnPlatform;
        this.nameOnPlatform = nameOnPlatform;
    }
}
