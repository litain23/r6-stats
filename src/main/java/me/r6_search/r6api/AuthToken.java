package me.r6_search.r6api;

import lombok.*;

@Getter
@Setter
@ToString
public class AuthToken {
    private String platformType;
    private String ticket;
    private String twoFactorAuthenticationTicket;
    private String profileId;
    private String userId;
    private String nameOnPlatform;
    private String environment;
    private String expiration;
    private String spaceId;
    private String clientIp;
    private String clientIpCountry;
    private String serverTime;
    private String sessionId;
    private String sessionKey;
    private String rememberMeTicket;
}
