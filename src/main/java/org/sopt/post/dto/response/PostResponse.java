package org.sopt.post.dto.response;

import org.sopt.post.domain.Post;
import org.sopt.post.domain.PostCategory;

import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        String title,
        String content,
        PostCategory category,
        Long memberId,
        String memberName,
        LocalDateTime createdAt,
        long viewCount
) {
    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCategory(),
                post.getMember().getId(),
                post.getMember().getName(),
                post.getCreatedAt(),
                post.getViewCount()
        );
    }
}