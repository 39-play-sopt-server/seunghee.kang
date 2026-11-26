package org.sopt.global.jwt;

public record TokenClaims(
        Long memberId,
        TokenType tokenType
) {
}
