package org.sopt.post.controller;

import org.sopt.global.response.ApiResponse;
import org.sopt.global.response.ResponseCode;
import org.sopt.post.domain.Post;
import org.sopt.post.dto.request.CreatePostRequest;
import org.sopt.post.dto.request.UpdatePostRequest;
import org.sopt.post.service.PostService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/posts")
public class PostController {
    // 생성자를 이용한 의존성 주입
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ApiResponse<Long> createPost(
            @RequestBody(required = true) CreatePostRequest request
    ) {
        Long postId = postService.createPost(
                request.title(),
                request.content(),
                request.category(),
                request.author()
        );
        return ApiResponse.success(ResponseCode.CREATED, postId);
    }

    @GetMapping
    public ApiResponse<List<Post>> readPosts(
            @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        List<Post> posts = postService.getPosts();
        return ApiResponse.success(ResponseCode.OK, posts);
    }

    @GetMapping(path = "/{postId}")
    public ApiResponse<Post> readPost(
            @PathVariable(name = "postId") Long postId
    ) {
        Post post = postService.getPost(postId);
        return ApiResponse.success(ResponseCode.OK, post);
    }

    @PutMapping(path = "/{postId}")
    public ApiResponse<Void> updatePost(
            @PathVariable(name = "postId") Long postId,
            @RequestBody(required = true) UpdatePostRequest request
    ) {
        postService.updatePost(postId, request.title(), request.content());
        return ApiResponse.success(ResponseCode.UPDATED);
    }

    @DeleteMapping(path = "/{postId}")
    public ApiResponse<Void> deletePost(
            @PathVariable(name = "postId") Long postId
    ) {
        postService.deletePost(postId);
        return ApiResponse.success(ResponseCode.DELETED);
    }
}
