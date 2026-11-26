package org.sopt.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.sopt.global.response.ApiResponse;
import org.springframework.http.ResponseEntity;

@Tag(name = "Member", description = "회원 API (Authorization: Bearer {accessToken} 필요)")
public interface MemberApi {

    @Operation(summary = "내 정보 조회", description = "Access Token 의 회원 id 를 반환합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "토큰이 없거나 유효하지 않음")
    })
    ResponseEntity<ApiResponse<Long>> me(@Parameter(hidden = true) Long memberId);

    @Operation(summary = "회원 탈퇴", description = "로그인한 회원 본인을 탈퇴시키고 Refresh Token 쿠키를 삭제합니다. 작성한 게시글이 있는 회원은 탈퇴할 수 없습니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "회원 탈퇴 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "토큰이 없거나 유효하지 않음"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "MEMBER_NOT_FOUND (이미 탈퇴한 회원의 Access Token 으로 요청)",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(
                            value = "{\"code\": \"MEMBER_NOT_FOUND\", \"message\": \"존재하지 않는 회원입니다.\", \"data\": null}"))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "409",
                    description = "MEMBER_HAS_POSTS",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(
                            value = "{\"code\": \"MEMBER_HAS_POSTS\", \"message\": \"작성한 게시글이 있는 회원은 삭제할 수 없습니다.\", \"data\": null}"))
            )
    })
    ResponseEntity<ApiResponse<Void>> withdraw(@Parameter(hidden = true) Long memberId);
}
