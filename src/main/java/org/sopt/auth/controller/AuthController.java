package org.sopt.auth.controller;

import org.sopt.auth.dto.request.LoginRequest;
import org.sopt.auth.dto.request.SignupRequest;
import org.sopt.auth.dto.response.LoginResponse;
import org.sopt.auth.service.AuthService;
import org.sopt.global.response.ApiResponse;
import org.sopt.global.response.SuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Long>> signup(@RequestBody SignupRequest request) {
        Long memberId = authService.signup(request.name(), request.email(), request.password());
        return ResponseEntity
                .status(SuccessCode.SIGNUP_SUCCESS.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.SIGNUP_SUCCESS, memberId));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        String accessToken = authService.login(request.email(), request.password());
        return ResponseEntity
                .status(SuccessCode.LOGIN_SUCCESS.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.LOGIN_SUCCESS, new LoginResponse(accessToken)));
    }
}
