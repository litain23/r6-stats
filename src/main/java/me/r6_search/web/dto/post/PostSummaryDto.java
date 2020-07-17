package me.r6_search.web.dto.post;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class PostSummaryDto {
    long postId;
    String title;
    String author;
    int recommendCnt;
    int viewCnt;
    boolean hasImg;
    boolean isNotice;
    LocalDateTime createdTime;
}
