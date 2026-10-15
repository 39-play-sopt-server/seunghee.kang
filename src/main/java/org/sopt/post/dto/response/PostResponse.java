package org.sopt.post.dto.response;

import org.sopt.post.domain.Post;
import org.sopt.post.domain.PostCategory;

import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        String title,
        String content,
        PostCategory category,
        String author,
        LocalDateTime createdAt
) {
    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCategory(),
                post.getAuthor(),
                post.getCreatedAt()
        );
    }
}