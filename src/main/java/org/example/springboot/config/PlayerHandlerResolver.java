package org.example.springboot.config;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.player.Player;
import org.example.springboot.service.PlayerService;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequiredArgsConstructor
public class PlayerHandlerResolver implements HandlerMethodArgumentResolver {
    private final PlayerService playerService;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        PlayerAnnotation playerAnnotation = parameter.getParameterAnnotation(PlayerAnnotation.class);
        return playerAnnotation != null && Player.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Map<String, String> pathVariables = getPathVariables(webRequest);
        String platform = pathVariables.get("platform");
        String userId = pathVariables.get("id");
        return playerService.findPlayerIfNotExistReturnNewEntity(platform, userId);
    }

    private Map<String, String> getPathVariables(NativeWebRequest webRequest) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        return (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    }
}
