package org.sopt.global.response;

import org.springframework.http.HttpStatus;

public enum SuccessCode {
    POST_CREATED(HttpStatus.CREATED, "POST_CREATED", "게시글이 생성되었습니다."),
    POST_LIST_READ(HttpStatus.OK, "POST_LIST_READ", "게시글 목록이 조회되었습니다."),
    POST_READ(HttpStatus.OK, "POST_READ", "게시글이 조회되었습니다."),
    POST_UPDATED(HttpStatus.OK, "POST_UPDATED", "게시글이 수정되었습니다."),
    POST_DELETED(HttpStatus.OK, "POST_DELETED", "게시글이 삭제되었습니다.");

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
