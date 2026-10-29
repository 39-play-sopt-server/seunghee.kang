package org.sopt.member.controller;

import org.sopt.global.response.ApiResponse;
import org.sopt.global.response.SuccessCode;
import org.sopt.member.dto.request.CreateMemberRequest;
import org.sopt.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/members")
public class MemberController implements MemberApi {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createMember(
            @RequestBody CreateMemberRequest request
    ) {
        Long memberId = memberService.createMember(request.name(), request.email());
        return ResponseEntity
                .status(SuccessCode.MEMBER_CREATED.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.MEMBER_CREATED, memberId));
    }

    @Override
    @DeleteMapping(path = "/{memberId}")
    public ResponseEntity<ApiResponse<Void>> deleteMember(
            @PathVariable(name = "memberId") Long memberId
    ) {
        memberService.deleteMember(memberId);
        return ResponseEntity
                .status(SuccessCode.MEMBER_DELETED.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.MEMBER_DELETED));
    }
}