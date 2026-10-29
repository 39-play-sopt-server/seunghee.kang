package org.sopt.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.sopt.global.entity.BaseEntity;
import org.sopt.global.response.ErrorCode;
import org.sopt.member.exception.InvalidMemberException;

@Entity
public class Member extends BaseEntity {
    private static final int MAX_NAME_LENGTH = 20;
    private static final int MAX_EMAIL_LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = MAX_NAME_LENGTH)
    private String name;

    @Column(nullable = false, length = MAX_EMAIL_LENGTH, unique = true)
    private String email;

    protected Member() {}

    public Member(String name, String email) {
        validateName(name);
        validateEmail(email);
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidMemberException(ErrorCode.MEMBER_NAME_BLANK);
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new InvalidMemberException(ErrorCode.MEMBER_NAME_TOO_LONG);
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new InvalidMemberException(ErrorCode.MEMBER_EMAIL_BLANK);
        }
        if (email.length() > MAX_EMAIL_LENGTH) {
            throw new InvalidMemberException(ErrorCode.MEMBER_EMAIL_TOO_LONG);
        }
    }
}