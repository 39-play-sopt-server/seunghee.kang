package org.sopt.post.domain;

import jakarta.persistence.*;
import org.sopt.global.entity.BaseEntity;
import org.sopt.global.response.ErrorCode;
import org.sopt.member.domain.Member;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private long viewCount;

    protected Post() {}

    public Post(String title, String content, PostCategory category, Member member) {
        validateTitle(title);
        validateContent(content);
        validateCategory(category);
        validateMember(member);
        this.title = title;
        this.content = content;
        this.category = category;
        this.member = member;
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

    public Member getMember() {
        return this.member;
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

    private void validateCategory(PostCategory category) {
        if (category == null) {
            throw new InvalidPostException(ErrorCode.POST_CATEGORY_REQUIRED);
        }
    }

    private void validateMember(Member member) {
        if (member == null) {
            throw new InvalidPostException(ErrorCode.POST_MEMBER_REQUIRED);
        }
    }
}