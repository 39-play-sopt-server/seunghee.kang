package org.sopt.post.domain;

import jakarta.persistence.*;
import org.sopt.global.entity.BaseEntity;
import org.sopt.global.response.ErrorCode;
import org.sopt.post.exception.InvalidPostException;

@Entity
public class Post extends BaseEntity {
    private static final int MAX_TITLE_LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = MAX_TITLE_LENGTH)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PostCategory category;

    private String author;

    @Column(nullable = false)
    private long viewCount;

    protected Post() {}

    public Post(String title, String content, PostCategory category, String author) {
        validateTitle(title);
        validateContent(content);
        validateAuthor(author);
        this.title = title;
        this.content = content;
        this.category = category;
        this.author = author;
        this.viewCount = 0;
    }

    public Long getId() {
        return this.id;
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

    public long getViewCount() {
        return this.viewCount;
    }

    public void update(String newTitle, String newContent) {
        validateTitle(newTitle);
        validateContent(newContent);
        this.title = newTitle;
        this.content = newContent;
    }

    public void increaseViewCount() {
        this.viewCount++;
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new InvalidPostException(ErrorCode.POST_TITLE_BLANK);
        }
        if (title.length() > MAX_TITLE_LENGTH) {
            throw new InvalidPostException(ErrorCode.POST_TITLE_TOO_LONG);
        }
    }

    private void validateContent(String content) {
        if (content == null || content.isBlank()) {
            throw new InvalidPostException(ErrorCode.POST_CONTENT_BLANK);
        }
    }

    private void validateAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new InvalidPostException(ErrorCode.POST_AUTHOR_BLANK);
        }
    }
}