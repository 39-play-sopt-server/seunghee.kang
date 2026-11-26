package org.sopt.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;
import org.sopt.global.response.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenVerifier implements TokenVerifier {

    private final JwtParser jwtParser;

    public JwtTokenVerifier(SecretKey secretKey) {
        this.jwtParser = Jwts.parser()
                .verifyWith(secretKey)
                .build();
    }

    @Override
    public TokenClaims verify(String token, TokenType expectedType) {
        Claims claims = parseClaims(token);

        TokenType actualType = TokenType.from(claims.get(TokenType.CLAIM_NAME, String.class))
                .orElseThrow(() -> new InvalidTokenException(ErrorCode.INVALID_TOKEN));
        if (actualType != expectedType) {
            throw new InvalidTokenException(ErrorCode.INVALID_TOKEN);
        }

        return new TokenClaims(parseMemberId(claims), actualType);
    }

    private Claims parseClaims(String token) {
        try {
            return jwtParser.parseSignedClaims(token).getPayload();
        } catch (ExpiredJwtException e) {
            throw new InvalidTokenException(ErrorCode.EXPIRED_TOKEN);
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException(ErrorCode.INVALID_TOKEN);
        }
    }

    private Long parseMemberId(Claims claims) {
        try {
            return Long.valueOf(claims.getSubject());
        } catch (NumberFormatException e) {
            throw new InvalidTokenException(ErrorCode.INVALID_TOKEN);
        }
    }
}
