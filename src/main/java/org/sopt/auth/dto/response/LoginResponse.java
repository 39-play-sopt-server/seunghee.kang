package org.sopt.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponse(
        @Schema(description = "액세스 토큰 (JWT)", example = "eyJhbGciOiJIUzI1NiJ9...")
        String accessToken
) {
}
