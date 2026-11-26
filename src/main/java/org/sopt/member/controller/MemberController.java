package org.sopt.member.controller;

import org.sopt.auth.cookie.RefreshTokenCookieProvider;
import org.sopt.global.response.ApiResponse;
import org.sopt.global.response.SuccessCode;
import org.sopt.member.service.MemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/members")
public class MemberController implements MemberApi {
    private final MemberService memberService;
    private final RefreshTokenCookieProvider refreshTokenCookieProvider;

    public MemberController(MemberService memberService, RefreshTokenCookieProvider refreshTokenCookieProvider) {
        this.memberService = memberService;
        this.refreshTokenCookieProvider = refreshTokenCookieProvider;
    }

    @Override
    @GetMapping(path = "/me")
    public ResponseEntity<ApiResponse<Long>> me(@AuthenticationPrincipal Long memberId) {
        return ResponseEntity
                .status(SuccessCode.MEMBER_ME_READ.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.MEMBER_ME_READ, memberId));
    }

    @Override
    @DeleteMapping(path = "/me")
    public ResponseEntity<ApiResponse<Void>> withdraw(@AuthenticationPrincipal Long memberId) {
        memberService.withdraw(memberId);
        return ResponseEntity
                .status(SuccessCode.MEMBER_WITHDRAWN.getHttpStatus())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookieProvider.expire().toString())
                .body(ApiResponse.success(SuccessCode.MEMBER_WITHDRAWN));
    }
}
