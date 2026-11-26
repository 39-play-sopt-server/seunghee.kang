package org.sopt.global.jwt;

public interface TokenGenerator {

    String generate(Long memberId, TokenType tokenType);
}
