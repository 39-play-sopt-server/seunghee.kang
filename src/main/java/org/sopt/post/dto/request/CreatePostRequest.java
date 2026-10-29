package org.sopt.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import org.sopt.post.domain.PostCategory;

public record CreatePostRequest(
        @Schema(description = "제목 (최대 100자)", example = "3차 세미나 후기")
        String title,

        @Schema(description = "내용", example = "JPA 연관관계를 배웠습니다.")
        String content,

        @Schema(description = "카테고리 (INFO, NOTICE, FREE, QUESTION 중 하나)", example = "FREE")
        PostCategory category,

        @Schema(description = "작성자 회원 id", example = "1")
        Long memberId
) {
}
