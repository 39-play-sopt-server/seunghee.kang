package org.sopt.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.sopt.post.domain.Post;
import org.sopt.post.domain.PostCategory;

import java.time.LocalDateTime;

public record PostResponse(
        @Schema(description = "게시글 id", example = "1")
        Long id,

        @Schema(description = "제목", example = "3차 세미나 후기")
        String title,

        @Schema(description = "내용", example = "JPA 연관관계를 배웠습니다.")
        String content,

        @Schema(description = "카테고리 (INFO, NOTICE, FREE, QUESTION 중 하나)", example = "FREE")
        PostCategory category,

        @Schema(description = "작성자 회원 id", example = "1")
        Long memberId,

        @Schema(description = "작성자 이름", example = "강승희")
        String memberName,

        @Schema(description = "작성일시", example = "2026-10-29T21:39:29")
        LocalDateTime createdAt,

        @Schema(description = "조회수", example = "0")
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