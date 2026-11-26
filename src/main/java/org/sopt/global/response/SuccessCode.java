package org.sopt.global.response;

import org.springframework.http.HttpStatus;

public enum SuccessCode {
    POST_CREATED(HttpStatus.CREATED, "POST_CREATED", "게시글이 생성되었습니다."),
    POST_LIST_READ(HttpStatus.OK, "POST_LIST_READ", "게시글 목록이 조회되었습니다."),
    POST_READ(HttpStatus.OK, "POST_READ", "게시글이 조회되었습니다."),
    POST_UPDATED(HttpStatus.OK, "POST_UPDATED", "게시글이 수정되었습니다."),
    POST_DELETED(HttpStatus.OK, "POST_DELETED", "게시글이 삭제되었습니다."),
    MEMBER_WITHDRAWN(HttpStatus.OK, "MEMBER_WITHDRAWN", "회원 탈퇴가 완료되었습니다."),
    MEMBER_ME_READ(HttpStatus.OK, "MEMBER_ME_READ", "내 정보가 조회되었습니다."),
    SIGNUP_SUCCESS(HttpStatus.CREATED, "SIGNUP_SUCCESS", "회원가입이 완료되었습니다."),
    LOGIN_SUCCESS(HttpStatus.OK, "LOGIN_SUCCESS", "로그인에 성공했습니다."),
    LOGOUT_SUCCESS(HttpStatus.OK, "LOGOUT_SUCCESS", "로그아웃되었습니다."),
    TOKEN_REISSUED(HttpStatus.OK, "TOKEN_REISSUED", "토큰이 재발급되었습니다.");

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    SuccessCode(HttpStatus httpStatus, String code, String message) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
