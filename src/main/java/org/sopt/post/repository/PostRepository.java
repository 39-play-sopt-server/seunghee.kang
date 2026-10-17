package org.sopt.post.repository;

import org.sopt.post.domain.Post;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    private final List<Post> posts = new ArrayList<>();
    private Long sequence = 0L;

    public Post save(Post post) {
        post.assignId(++sequence);
        posts.add(post);
        return post;
    }

    public List<Post> findAll() {
        return List.copyOf(posts);
    }

    public Optional<Post> findById(Long id) {
        for (Post post : posts) {
            if (post.getId().equals(id)) {
                return Optional.of(post);
            }
        }
        return Optional.empty();
    }

    public boolean deleteById(Long id) {
        return posts.removeIf(post -> post.getId().equals(id));
    }
}
