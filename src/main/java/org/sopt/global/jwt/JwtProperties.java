package org.sopt.global.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
        String secret,
        long accessTokenExpiration,
        long refreshTokenExpiration
) {
    public long expirationOf(TokenType tokenType) {
        return switch (tokenType) {
            case ACCESS -> accessTokenExpiration;
            case REFRESH -> refreshTokenExpiration;
        };
    }
}
