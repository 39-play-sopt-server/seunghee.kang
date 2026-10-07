package org.sopt.post.controller;

import org.sopt.post.repository.PostRepository;
import org.sopt.post.view.PostView;
import org.sopt.post.domain.Post;

import java.util.List;

public class PostController {
    private final PostView view;
    private final PostRepository repository;

    public PostController(PostView view, PostRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void run() {
        while (true) {
            view.printMenu();
            int command = view.readCommand();
            switch (command) {
                case 1 -> createPost();
                case 2 -> readPosts();
                case 3 -> readPost();
                case 4 -> updatePost();
                case 5 -> deletePost();
                case 6 -> {
                    view.printMessage("프로그램을 종료합니다.");
                    return;
                }
                default -> view.printMessage("잘못된 입력입니다.");
            }
        }
    }

    private void createPost() {
        String title = view.readTitle();
        String content = view.readContent();
        Post post = new Post(title, content);
        repository.save(post);
        view.printMessage("게시글이 작성되었습니다.");
    }

    private void readPosts() {
        List<Post> posts = repository.findAll();

        if (posts.isEmpty()) {
            view.printMessage("게시글이 없습니다.");
            return;
        }
        for (int i = 0; i < posts.size(); i++) {
            view.printMessage((i + 1) + ". " + posts.get(i).getTitle());
        }
    }

    private void readPost() {
        List<Post> posts = repository.findAll();

        if (posts.isEmpty()) {
            view.printMessage("게시글이 없습니다.");
            return;
        }

        int index = view.readPostNumber("조회할 게시글 번호: ") - 1;

        Post post = repository.findById(index);

        if (post == null) {
            view.printMessage("존재하지 않는 게시글입니다.");
            return;
        }

        view.printPost(post);
    }

    private void updatePost() {
        List<Post> posts = repository.findAll();

        if (posts.isEmpty()) {
            view.printMessage("게시글이 없습니다.");
            return;
        }

        int index = view.readPostNumber("수정할 게시글 번호: ") - 1;

        Post post = repository.findById(index);

        if (post == null) {
            view.printMessage("존재하지 않는 게시글입니다.");
            return;
        }

        String newTitle = view.readTitle();
        String newContent = view.readContent();

        post.updateTitle(newTitle);
        post.updateContent(newContent);

        view.printMessage("게시글이 수정되었습니다.");
    }

    private void deletePost() {
        List<Post> posts = repository.findAll();

        if (posts.isEmpty()) {
            view.printMessage("게시글이 없습니다.");
            return;
        }

        int index = view.readPostNumber("삭제할 게시글 번호: ") - 1;

        if (!repository.deleteById(index)) {
            view.printMessage("존재하지 않는 게시글입니다.");
            return;
        }

        view.printMessage("게시글이 삭제되었습니다.");
    }

}