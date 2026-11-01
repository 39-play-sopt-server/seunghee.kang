package org.sopt.post.controller;

import org.sopt.global.response.ApiResponse;
import org.sopt.global.response.SuccessCode;
import org.sopt.post.dto.request.CreatePostRequest;
import org.sopt.post.dto.request.UpdatePostRequest;
import org.sopt.post.dto.response.PostListResponse;
import org.sopt.post.dto.response.PostResponse;
import org.sopt.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/posts")
public class PostController implements PostApi {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createPost(
            @RequestBody(required = true) CreatePostRequest request
    ) {
        Long postId = postService.createPost(
                request.title(),
                request.content(),
                request.category(),
                request.memberId()
        );
        return ResponseEntity
                .status(SuccessCode.POST_CREATED.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.POST_CREATED, postId));
    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<PostListResponse>> readPosts(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PostListResponse posts = postService.getPosts(page, size);
        return ResponseEntity
                .status(SuccessCode.POST_LIST_READ.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.POST_LIST_READ, posts));
    }

    @Override
    @GetMapping(path = "/{postId}")
    public ResponseEntity<ApiResponse<PostResponse>> readPost(
            @PathVariable(name = "postId") Long postId
    ) {
        PostResponse post = postService.getPost(postId);
        return ResponseEntity
                .status(SuccessCode.POST_READ.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.POST_READ, post));
    }

    @Override
    @PutMapping(path = "/{postId}")
    public ResponseEntity<ApiResponse<Void>> updatePost(
            @PathVariable(name = "postId") Long postId,
            @RequestBody(required = true) UpdatePostRequest request
    ) {
        postService.updatePost(postId, request.title(), request.content());
        return ResponseEntity
                .status(SuccessCode.POST_UPDATED.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.POST_UPDATED));
    }

    @Override
    @DeleteMapping(path = "/{postId}")
    public ResponseEntity<ApiResponse<Void>> deletePost(
            @PathVariable(name = "postId") Long postId
    ) {
        postService.deletePost(postId);
        return ResponseEntity
                .status(SuccessCode.POST_DELETED.getHttpStatus())
                .body(ApiResponse.success(SuccessCode.POST_DELETED));
    }
}
