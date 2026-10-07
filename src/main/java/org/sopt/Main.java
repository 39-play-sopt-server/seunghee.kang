package org.sopt;

import org.sopt.post.controller.PostController;
import org.sopt.post.repository.PostRepository;
import org.sopt.post.view.PostView;

public class Main {

    public static void main(String[] args) {
        PostView view = new PostView();
        PostRepository repository = new PostRepository();
        PostController controller = new PostController(view, repository);
        controller.run();
    }
}
