package org.sopt.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRequest(
        @Schema(description = "이메일", example = "test@sopt.org")
        String email,

        @Schema(description = "비밀번호", example = "password1234")
        String password
) {
}
