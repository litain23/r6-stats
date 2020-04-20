package org.example.springboot.service.operators;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.rankstat.RankStat;
import org.example.springboot.domain.rankstat.RankStatRepository;
import org.example.springboot.domain.user.User;
import org.example.springboot.domain.user.UserRepository;
import org.example.springboot.r6api.API;
import org.example.springboot.r6api.AuthToken;
import org.example.springboot.r6api.UbiAuthApi;
import org.example.springboot.web.dto.RankStatResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class RankStatService {
    private final RankStatRepository rankRepository;
    private final UserRepository userRepository;
    private AuthToken token;

    @Transactional
    public RankStatResponseDto getRankStat(String platform, String id, int season) {
        token = UbiAuthApi.getAuthToken();
        API api = new API(token);
        RankStat rankstat = parseResponseStr(api.getRankStat(platform, id, season));
        rankRepository.save(rankstat);

        User user = userRepository.findByPlatformAndAndUserId(platform, id);
        if(user == null) {
            user = userRepository.save(
                    User.builder()
                    .platform(platform)
                    .userId(id)
                    .build()
            );
        }
        user.getRankList().add(rankstat);

        return new RankStatResponseDto(rankstat);
    }

    private RankStat parseResponseStr(String strJson) {
        Gson gson = new GsonBuilder()
                .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        return gson.fromJson(strJson, RankStat.class);
    }

}
