package org.sopt.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateMemberRequest(
        @Schema(description = "이름 (최대 20자)", example = "강승희")
        String name,

        @Schema(description = "이메일 (최대 100자, 중복 불가)", example = "test@sopt.org")
        String email
) {
}
