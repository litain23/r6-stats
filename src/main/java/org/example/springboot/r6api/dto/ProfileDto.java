package org.example.springboot.r6api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class ProfileDto {
    private String profileId;
    private String userId;
    private String platformType;
    private String idOnPlatform;
    private String nameOnPlatform;
}
