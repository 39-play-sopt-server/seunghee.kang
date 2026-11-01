package org.sopt.post.dto.response;

import org.sopt.post.domain.Post;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public record PostListResponse(
        List<PostResponse> posts,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext
) {
    public static PostListResponse from(Page<Post> postPage) {
        List<PostResponse> posts = new ArrayList<>();
        for (Post post : postPage.getContent()) {
            posts.add(PostResponse.from(post));
        }
        return new PostListResponse(
                posts,
                postPage.getNumber() + 1,
                postPage.getSize(),
                postPage.getTotalElements(),
                postPage.getTotalPages(),
                postPage.hasNext()
        );
    }
}
