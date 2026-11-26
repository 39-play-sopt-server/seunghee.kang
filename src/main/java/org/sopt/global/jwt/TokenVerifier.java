package org.sopt.global.jwt;

public interface TokenVerifier {

    TokenClaims verify(String token, TokenType expectedType);
}
