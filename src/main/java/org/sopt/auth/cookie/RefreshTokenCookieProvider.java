package org.sopt.auth.cookie;

import java.time.Duration;
import org.sopt.global.jwt.JwtProperties;
import org.sopt.global.jwt.TokenType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenCookieProvider {

    public static final String COOKIE_NAME = "refreshToken";

    private static final String COOKIE_PATH = "/api/v1/auth";

    private final Duration maxAge;

    public RefreshTokenCookieProvider(JwtProperties jwtProperties) {
        this.maxAge = Duration.ofMillis(jwtProperties.expirationOf(TokenType.REFRESH));
    }

    public ResponseCookie create(String refreshToken) {
        return baseCookie(refreshToken)
                .maxAge(maxAge)
                .build();
    }

    public ResponseCookie expire() {
        return baseCookie("")
                .maxAge(Duration.ZERO)
                .build();
    }

    private ResponseCookie.ResponseCookieBuilder baseCookie(String value) {
        return ResponseCookie.from(COOKIE_NAME, value)
                .httpOnly(true)
                .secure(true)
                .sameSite("Lax")
                .path(COOKIE_PATH);
    }
}
