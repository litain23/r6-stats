package me.r6_search.service;

import lombok.RequiredArgsConstructor;
import me.r6_search.r6api.UbiApi;
import me.r6_search.r6api.dto.ProfileDto;
import me.r6_search.domain.player.Player;
import me.r6_search.domain.player.PlayerRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final UbiApi ubiApi;

    public Player findPlayerIfNotExistReturnNewEntity(String platform, String id) {
        // 외부 API 서버가 띄어쓰기를 + 로 구분. 저장할때는 원래 아이디지만, api에 요청할때는 +로 바꿔서 요청
        String replaceSpaceId = id.replaceAll(" ", "+");
        ProfileDto profileDto = ubiApi.getProfile(platform, replaceSpaceId);

        // ID 는 변경할 수 있기 때문에, profileId(고유아이디)로 검색
        Player player = playerRepository.findByPlatformAndProfileId(platform, profileDto.getProfileId());
        if(player == null) {
            // 없을 시 자동으로 throw exception
            ubiApi.checkIsExistUserId(platform, profileDto.getProfileId());
            player = Player.builder()
                    .profileId(profileDto.getProfileId())
                    .platform(platform)
                    .userId(id)
                    .build();

            playerRepository.save(player);
        } else {
            // 아이디가 변경됬으면 최신 아이디로 변경
            if(!player.getUserId().equals(id)) {
                player.updateUserId(id);
            }
        }
        return player;
    }
}
