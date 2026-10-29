package org.sopt.post.dto.request;

import org.sopt.post.domain.PostCategory;

public record CreatePostRequest(
        String title,
        String content,
        PostCategory category,
        Long memberId
) {
}