package org.sopt.post.domain;

import java.time.LocalDateTime;

public class Post {
    private String title;
    private String content;
    private PostCategory category;
    private String author;
    private LocalDateTime createdAt;

    public Post(String title, String content, PostCategory category, String author) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.author = author;
        this.createdAt = LocalDateTime.now();
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public PostCategory getCategory() {
        return this.category;
    }

    public String getAuthor() {
        return this.author;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}