package org.sopt.global.jwt;

import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenGenerator implements TokenGenerator {

    private final SecretKey secretKey;
    private final JwtProperties jwtProperties;

    public JwtTokenGenerator(SecretKey secretKey, JwtProperties jwtProperties) {
        this.secretKey = secretKey;
        this.jwtProperties = jwtProperties;
    }

    @Override
    public String generate(Long memberId, TokenType tokenType) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtProperties.expirationOf(tokenType));

        return Jwts.builder()
                .subject(String.valueOf(memberId))
                .claim(TokenType.CLAIM_NAME, tokenType.name())
                .issuedAt(now)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
    }
}
