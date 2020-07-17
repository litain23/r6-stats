package me.r6_search.web.dto.post;

import lombok.Data;
import me.r6_search.domain.post.Post;
import me.r6_search.domain.userprofile.UserProfile;

@Data
public class PostSaveRequestDto {
    String title;
    String content;
    String type;

    public Post toEntity(UserProfile userProfile) {
        return Post.builder()
                .content(content)
                .title(title)
                .type(type)
                .userProfile(userProfile)
                .build();
    }
}
