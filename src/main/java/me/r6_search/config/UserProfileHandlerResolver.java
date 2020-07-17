package me.r6_search.config;

import lombok.RequiredArgsConstructor;
import me.r6_search.domain.userprofile.UserProfile;
import me.r6_search.security.UserProfileDetails;
import me.r6_search.service.UserProfileService;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
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
    public UserProfile resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Principal user = webRequest.getUserPrincipal();
        if(user == null) {
            return null;
        }

        UserProfileDetails details = (UserProfileDetails) ((Authentication) user).getPrincipal();
        return userProfileService.getUserProfile(details.getUsername());
    }
}
