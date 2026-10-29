package org.sopt.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.sopt.global.response.ApiResponse;
import org.sopt.member.dto.request.CreateMemberRequest;
import org.springframework.http.ResponseEntity;

@Tag(name = "Member", description = "회원 API")
public interface MemberApi {

    @Operation(summary = "회원 생성", description = "이름과 이메일로 회원을 생성합니다. 생성된 회원 id를 반환합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "회원 생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "MEMBER_NAME_BLANK, MEMBER_NAME_TOO_LONG, MEMBER_EMAIL_BLANK, MEMBER_EMAIL_TOO_LONG, INVALID_REQUEST_BODY",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(
                            value = "{\"code\": \"MEMBER_NAME_BLANK\", \"message\": \"이름은 비어있을 수 없습니다.\", \"data\": null}"))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "409",
                    description = "DUPLICATE_EMAIL",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(
                            value = "{\"code\": \"DUPLICATE_EMAIL\", \"message\": \"이미 사용 중인 이메일입니다.\", \"data\": null}"))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "415",
                    description = "UNSUPPORTED_MEDIA_TYPE",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(
                            value = "{\"code\": \"UNSUPPORTED_MEDIA_TYPE\", \"message\": \"지원하지 않는 Content-Type입니다.\", \"data\": null}"))
            )
    })
    ResponseEntity<ApiResponse<Long>> createMember(CreateMemberRequest request);

    @Operation(summary = "회원 삭제", description = "회원을 삭제합니다. 작성한 게시글이 있는 회원은 삭제할 수 없습니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "회원 삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "MEMBER_NOT_FOUND",
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
    ResponseEntity<ApiResponse<Void>> deleteMember(
            @Parameter(description = "회원 id", example = "1") Long memberId
    );
}
