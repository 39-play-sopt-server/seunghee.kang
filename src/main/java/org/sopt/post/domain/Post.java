package org.sopt.post.domain;

import org.sopt.global.response.ErrorCode;
import org.sopt.post.exception.InvalidPostException;

import java.time.LocalDateTime;

public class Post {
    private Long id;
    private String title;
    private String content;
    private PostCategory category;
    private final String author;
    private final LocalDateTime createdAt;
    private long viewCount;

    public Post(String title, String content, PostCategory category, String author) {
        validateTitle(title);
        validateContent(content);
        this.title = title;
        this.content = content;
        this.category = category;
        this.author = author;
        this.createdAt = LocalDateTime.now();
        this.viewCount = 0;
    }

    public Long getId() {
        return this.id;
    }

    public void assignId(Long id) {
        this.id = id;
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

    public long getViewCount() {
        return this.viewCount;
    }

    public void update(String title, String content) {
        validateTitle(title);
        validateContent(content);
        this.title = title;
        this.content = content;
    }

    public void increaseViewCount() {
        this.viewCount++;
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new InvalidPostException(ErrorCode.POST_TITLE_BLANK);
        }
    }

    private void validateContent(String content) {
        if (content == null || content.isBlank()) {
            throw new InvalidPostException(ErrorCode.POST_CONTENT_BLANK);
        }
    }
}