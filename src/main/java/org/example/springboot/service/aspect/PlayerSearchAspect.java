package org.example.springboot.service.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.springboot.domain.player.Player;
import org.example.springboot.service.PlayerService;
import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Aspect
public class PlayerSearchAspect {
    private final PlayerService playerService;

    @Around("@annotation(org.example.springboot.service.aspect.ConvertIdToProfileId)")
    public Object sayHello(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        String platform = args[0].toString();
        String id = args[1].toString();

        Player player = playerService.findPlayerIfNotExistReturnNewEntity(platform, id);
        args[1] = player.getProfileId();
        return pjp.proceed(args);
    }
}
