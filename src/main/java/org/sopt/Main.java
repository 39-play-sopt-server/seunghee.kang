package org.sopt;

import org.sopt.post.controller.PostController;
import org.sopt.post.repository.PostRepository;
import org.sopt.post.service.PostService;
import org.sopt.post.view.PostView;

public class Main {

    public static void main(String[] args) {
        PostView view = new PostView();
        PostRepository repository = new PostRepository();
        PostService service = new PostService(repository);
        PostController controller = new PostController(view, service);
        controller.run();
    }
}
