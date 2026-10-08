package org.sopt.post.service;

import org.sopt.post.domain.Post;
import org.sopt.post.domain.PostCategory;
import org.sopt.post.repository.PostRepository;

import java.util.List;

public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public void createPost(String title, String content, PostCategory category, String author) {
        Post post = new Post(title, content, category, author);
        repository.save(post);
    }

    public List<Post> getPosts() {
        return repository.findAll();
    }

    public Post getPost(int index) {
        return repository.findById(index);
    }

    public boolean updatePost(int index, String title, String content){
        Post post = repository.findById(index);
        if (post != null) {
            post.updateTitle(title);
            post.updateContent(content);
            return true;
        }
        return false;
    }

    public boolean deletePost(int index) {
        return repository.deleteById(index);
    }

}
