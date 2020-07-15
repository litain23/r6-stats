package org.example.springboot.config;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.player.Player;
import org.example.springboot.domain.userprofile.UserProfile;
import org.example.springboot.domain.userprofile.UserProfileRepository;
import org.example.springboot.security.UserProfileDetails;
import org.example.springboot.service.UserProfileService;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.security.Principal;

@RequiredArgsConstructor
public class UserProfileHandlerResolver implements HandlerMethodArgumentResolver {
    private final UserProfileService userProfileService;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        UserProfileAnnotation userProfileAnnotation = parameter.getParameterAnnotation(UserProfileAnnotation.class);
        return userProfileAnnotation != null && UserProfile.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Principal user = webRequest.getUserPrincipal();
        UserProfileDetails details = (UserProfileDetails) ((Authentication) user).getPrincipal();
        return userProfileService.getUserProfile(details.getUsername());
    }
}
