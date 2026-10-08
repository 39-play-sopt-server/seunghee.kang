package org.sopt.post.controller;

import org.sopt.post.domain.PostCategory;
import org.sopt.post.service.PostService;
import org.sopt.post.view.PostView;
import org.sopt.post.domain.Post;

import java.util.List;

public class PostController {
    private final PostView view;
    private final PostService service;

    public PostController(PostView view, PostService service) {
        this.view = view;
        this.service = service;
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
        PostCategory category = view.readCategory();
        String author = view.readAuthor();
        service.createPost(title, content, category, author);
        view.printMessage("게시글이 작성되었습니다.");
    }

    private void readPosts() {
        List<Post> posts = service.getPosts();

        if (posts.isEmpty()) {
            view.printMessage("게시글이 없습니다.");
            return;
        }
        for (int i = 0; i < posts.size(); i++) {
            view.printMessage((i + 1) + ". " + posts.get(i).getTitle());
        }
    }

    private void readPost() {
        List<Post> posts = service.getPosts();

        if (posts.isEmpty()) {
            view.printMessage("게시글이 없습니다.");
            return;
        }

        int index = view.readPostNumber("조회할 게시글 번호: ") - 1;

        Post post = service.getPost(index);

        if (post == null) {
            view.printMessage("존재하지 않는 게시글입니다.");
            return;
        }

        view.printPost(post);
    }

    private void updatePost() {
        List<Post> posts = service.getPosts();

        if (posts.isEmpty()) {
            view.printMessage("게시글이 없습니다.");
            return;
        }

        int index = view.readPostNumber("수정할 게시글 번호: ") - 1;

        String newTitle = view.readTitle();
        String newContent = view.readContent();

        if (!service.updatePost(index, newTitle, newContent)) {
            view.printMessage("존재하지 않는 게시글입니다.");
            return;
        }

        view.printMessage("게시글이 수정되었습니다.");
    }

    private void deletePost() {
        List<Post> posts = service.getPosts();

        if (posts.isEmpty()) {
            view.printMessage("게시글이 없습니다.");
            return;
        }

        int index = view.readPostNumber("삭제할 게시글 번호: ") - 1;

        if (!service.deletePost(index)) {
            view.printMessage("존재하지 않는 게시글입니다.");
            return;
        }

        view.printMessage("게시글이 삭제되었습니다.");
    }

}