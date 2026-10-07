package org.sopt.post.repository;

import org.sopt.post.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    private final List<Post> posts = new ArrayList<>();

    public void save(Post post) {
        posts.add(post);
    }

    public List<Post> findAll() {
        return posts;
    }

    public Post findById(int index) {
        if (isValidIndex(index)) {
            return posts.get(index);
        }
        return null;
    }

    public boolean deleteById(int index) {
        if (isValidIndex(index)) {
            posts.remove(index);
            return true;
        }
        return false;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < posts.size();
    }
}
