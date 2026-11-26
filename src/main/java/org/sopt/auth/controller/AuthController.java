package org.sopt.auth.controller;

import org.sopt.auth.cookie.RefreshTokenCookieProvider;
import org.sopt.auth.dto.TokenPair;
import org.sopt.auth.dto.request.LoginRequest;
import org.sopt.auth.dto.request.SignupRequest;
import org.sopt.auth.service.AuthService;
import org.sopt.global.response.ApiResponse;
import org.sopt.global.response.SuccessCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private static final String BEARER_PREFIX = "Bearer ";

    private final AuthService authService;
    private final RefreshTokenCookieProvider refreshTokenCookieProvider;

    public AuthController(AuthService authService, RefreshTokenCookieProvider refreshTokenCookieProvider) {
        this.authService = authService;
        this.refreshTokenCookieProvider = refreshTokenCookieProvider;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Long>> signup(@RequestBody SignupRequest request) {
        Long memberId = authService.signup(request.name(), request.email(), request.password());
        return ResponseEntity
                .status(SuccessCode.SIGNUP_SUCCESS.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.SIGNUP_SUCCESS, memberId));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Void>> login(@RequestBody LoginRequest request) {
        TokenPair tokens = authService.login(request.email(), request.password());
        return respondWithTokens(tokens, SuccessCode.LOGIN_SUCCESS);
    }

    @PostMapping("/reissue")
    public ResponseEntity<ApiResponse<Void>> reissue(
            @CookieValue(name = RefreshTokenCookieProvider.COOKIE_NAME, required = false) String refreshToken
    ) {
        TokenPair tokens = authService.reissue(refreshToken);
        return respondWithTokens(tokens, SuccessCode.TOKEN_REISSUED);
    }

    private ResponseEntity<ApiResponse<Void>> respondWithTokens(TokenPair tokens, SuccessCode successCode) {
        return ResponseEntity
                .status(successCode.getHttpStatus())
                .header(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + tokens.accessToken())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookieProvider.create(tokens.refreshToken()).toString())
                .body(ApiResponse.success(successCode));
    }
}
