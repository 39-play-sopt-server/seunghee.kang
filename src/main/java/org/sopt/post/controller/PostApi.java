package org.sopt.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.sopt.global.response.ApiResponse;
import org.sopt.post.dto.request.CreatePostRequest;
import org.sopt.post.dto.request.UpdatePostRequest;
import org.sopt.post.dto.response.PostListResponse;
import org.sopt.post.dto.response.PostResponse;
import org.springframework.http.ResponseEntity;

@Tag(name = "Post", description = "게시글 API")
public interface PostApi {

    @Operation(summary = "게시글 작성", description = "회원 id를 작성자로 하여 게시글을 작성합니다. 생성된 게시글 id를 반환합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "게시글 작성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "POST_TITLE_BLANK, POST_TITLE_TOO_LONG, POST_CONTENT_BLANK, POST_CATEGORY_REQUIRED, POST_MEMBER_REQUIRED, INVALID_REQUEST_BODY",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(
                            value = "{\"code\": \"POST_TITLE_BLANK\", \"message\": \"제목은 비어있을 수 없습니다.\", \"data\": null}"))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "MEMBER_NOT_FOUND",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(
                            value = "{\"code\": \"MEMBER_NOT_FOUND\", \"message\": \"존재하지 않는 회원입니다.\", \"data\": null}"))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "415",
                    description = "UNSUPPORTED_MEDIA_TYPE",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(
                            value = "{\"code\": \"UNSUPPORTED_MEDIA_TYPE\", \"message\": \"지원하지 않는 Content-Type입니다.\", \"data\": null}"))
            )
    })
    ResponseEntity<ApiResponse<Long>> createPost(CreatePostRequest request);

    @Operation(summary = "게시글 목록 조회", description = "게시글 목록을 생성일 기준 최신순으로 페이지 단위로 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "게시글 목록 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "INVALID_PARAMETER (page가 1 미만, size가 1 미만이거나 50 초과, 숫자가 아닌 값)",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(
                            value = "{\"code\": \"INVALID_PARAMETER\", \"message\": \"요청 파라미터의 형식이 올바르지 않습니다.\", \"data\": null}"))
            )
    })
    ResponseEntity<ApiResponse<PostListResponse>> readPosts(
            @Parameter(description = "페이지 번호 (1부터 시작)", example = "1") int page,
            @Parameter(description = "페이지 크기 (1~50)", example = "10") int size
    );

    @Operation(summary = "게시글 상세 조회", description = "게시글을 조회하고 조회수를 1 증가시킵니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "게시글 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "INVALID_PARAMETER (게시글 id가 숫자가 아님)",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(
                            value = "{\"code\": \"INVALID_PARAMETER\", \"message\": \"요청 파라미터의 형식이 올바르지 않습니다.\", \"data\": null}"))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "POST_NOT_FOUND",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(
                            value = "{\"code\": \"POST_NOT_FOUND\", \"message\": \"존재하지 않는 게시글입니다.\", \"data\": null}"))
            )
    })
    ResponseEntity<ApiResponse<PostResponse>> readPost(
            @Parameter(description = "게시글 id", example = "1") Long postId
    );

    @Operation(summary = "게시글 수정", description = "게시글의 제목과 내용을 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "게시글 수정 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "POST_TITLE_BLANK, POST_TITLE_TOO_LONG, POST_CONTENT_BLANK, INVALID_REQUEST_BODY, INVALID_PARAMETER",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(
                            value = "{\"code\": \"POST_CONTENT_BLANK\", \"message\": \"내용은 비어있을 수 없습니다.\", \"data\": null}"))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "POST_NOT_FOUND",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(
                            value = "{\"code\": \"POST_NOT_FOUND\", \"message\": \"존재하지 않는 게시글입니다.\", \"data\": null}"))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "415",
                    description = "UNSUPPORTED_MEDIA_TYPE",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(
                            value = "{\"code\": \"UNSUPPORTED_MEDIA_TYPE\", \"message\": \"지원하지 않는 Content-Type입니다.\", \"data\": null}"))
            )
    })
    ResponseEntity<ApiResponse<Void>> updatePost(
            @Parameter(description = "게시글 id", example = "1") Long postId,
            UpdatePostRequest request
    );

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "게시글 삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "POST_NOT_FOUND",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(
                            value = "{\"code\": \"POST_NOT_FOUND\", \"message\": \"존재하지 않는 게시글입니다.\", \"data\": null}"))
            )
    })
    ResponseEntity<ApiResponse<Void>> deletePost(
            @Parameter(description = "게시글 id", example = "1") Long postId
    );
}
