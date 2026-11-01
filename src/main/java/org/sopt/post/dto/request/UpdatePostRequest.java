package org.sopt.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdatePostRequest(
        @Schema(description = "수정할 제목 (최대 100자)", example = "3차 세미나 후기 (수정)")
        String title,

        @Schema(description = "수정할 내용", example = "JPA 연관관계와 페이지네이션을 배웠습니다.")
        String content
) {
}
