package org.sopt.auth.service;

import org.sopt.auth.dto.TokenPair;
import org.sopt.auth.exception.LoginFailedException;
import org.sopt.global.jwt.InvalidTokenException;
import org.sopt.global.jwt.TokenClaims;
import org.sopt.global.jwt.TokenGenerator;
import org.sopt.global.jwt.TokenType;
import org.sopt.global.jwt.TokenVerifier;
import org.sopt.global.response.ErrorCode;
import org.sopt.member.domain.Member;
import org.sopt.member.exception.DuplicateEmailException;
import org.sopt.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;
    private final TokenVerifier tokenVerifier;

    public AuthService(
            MemberRepository memberRepository,
            PasswordEncoder passwordEncoder,
            TokenGenerator tokenGenerator,
            TokenVerifier tokenVerifier
    ) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
        this.tokenVerifier = tokenVerifier;
    }

    @Transactional
    public Long signup(String name, String email, String password) {
        if (memberRepository.existsByEmail(email)) {
            throw new DuplicateEmailException(ErrorCode.DUPLICATE_EMAIL);
        }

        Member member = new Member(name, email, passwordEncoder.encode(password));
        return memberRepository.save(member).getId();
    }

    @Transactional(readOnly = true)
    public TokenPair login(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new LoginFailedException(ErrorCode.LOGIN_FAILED));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new LoginFailedException(ErrorCode.LOGIN_FAILED);
        }

        return issueTokens(member.getId());
    }

    @Transactional(readOnly = true)
    public TokenPair reissue(String refreshToken) {
        if (refreshToken == null || refreshToken.isBlank()) {
            throw new InvalidTokenException(ErrorCode.REFRESH_TOKEN_REQUIRED);
        }

        TokenClaims claims = tokenVerifier.verify(refreshToken, TokenType.REFRESH);

        if (!memberRepository.existsById(claims.memberId())) {
            throw new InvalidTokenException(ErrorCode.INVALID_TOKEN);
        }

        return issueTokens(claims.memberId());
    }

    private TokenPair issueTokens(Long memberId) {
        return new TokenPair(
                tokenGenerator.generate(memberId, TokenType.ACCESS),
                tokenGenerator.generate(memberId, TokenType.REFRESH)
        );
    }
}
