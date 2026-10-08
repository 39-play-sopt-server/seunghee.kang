package org.sopt.post.service;

import org.sopt.post.domain.Post;
import org.sopt.post.domain.PostCategory;
import org.sopt.post.exception.PostNotFoundException;
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
        Post post = repository.findById(index);
        if (post == null) {
            throw new PostNotFoundException("존재하지 않는 게시글입니다.");
        }
        return post;
    }

    public void updatePost(int index, String title, String content){
        Post post = getPost(index);
        post.updateTitle(title);
        post.updateContent(content);
    }

    public void deletePost(int index) {
        if (!repository.deleteById(index)) {
            throw new PostNotFoundException("삭제할 게시글이 존재하지 않습니다.");
        }
    }

}
