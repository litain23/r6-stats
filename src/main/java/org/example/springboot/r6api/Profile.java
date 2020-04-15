package org.example.springboot.r6api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Profile {
    private String profileId;
    private String userId;
    private String platformType;
    private String idOnPlatform;
    private String nameOnPlatform;
}
