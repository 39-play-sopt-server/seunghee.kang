package org.sopt.post.service;

import org.sopt.post.domain.Post;
import org.sopt.post.domain.PostCategory;
import org.sopt.post.dto.response.PostResponse;
import org.sopt.post.exception.PostNotFoundException;
import org.sopt.post.repository.PostRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Long createPost(String title, String content, PostCategory category, String author) {
        Post post = new Post(title, content, category, author);
        return repository.save(post).getId();
    }

    public List<PostResponse> getPosts() {
        List<PostResponse> responses = new ArrayList<>();
        for (Post post : repository.findAll()) {
            responses.add(PostResponse.from(post));
        }
        return responses;
    }

    public PostResponse getPost(Long postId) {
        Post post = findPost(postId);
        return PostResponse.from(post);
    }

    public void updatePost(Long postId, String title, String content) {
        Post post = findPost(postId);
        post.update(title, content);
    }

    public void deletePost(Long postId) {
        if (!repository.deleteById(postId)) {
            throw new PostNotFoundException("삭제할 게시글이 존재하지 않습니다.");
        }
    }

    private Post findPost(Long postId) {
        Post post = repository.findById(postId);
        if (post == null) {
            throw new PostNotFoundException("게시글이 존재하지 않습니다.");
        }
        return post;
    }

}
