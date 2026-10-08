package org.sopt.post.domain;

import org.sopt.post.exception.InvalidPostException;

import java.time.LocalDateTime;

public class Post {
    private String title;
    private String content;
    private PostCategory category;
    private final String author;
    private final LocalDateTime createdAt;

    public Post(String title, String content, PostCategory category, String author) {
        validateTitle(title);
        validateContent(content);
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
        validateTitle(title);
        this.title = title;
    }

    public void updateContent(String content) {
        validateContent(content);
        this.content = content;
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new InvalidPostException("제목은 비어있을 수 없습니다.");
        }
    }

    private void validateContent(String content) {
        if (content == null || content.isBlank()) {
            throw new InvalidPostException("내용은 비어있을 수 없습니다.");
        }
    }
}