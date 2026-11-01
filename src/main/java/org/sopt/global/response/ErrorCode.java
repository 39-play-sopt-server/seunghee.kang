package org.sopt.global.response;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST_NOT_FOUND", "존재하지 않는 게시글입니다."),
    POST_TITLE_BLANK(HttpStatus.BAD_REQUEST, "POST_TITLE_BLANK", "제목은 비어있을 수 없습니다."),
    POST_TITLE_TOO_LONG(HttpStatus.BAD_REQUEST, "POST_TITLE_TOO_LONG", "제목은 100자 이하여야 합니다."),
    POST_CATEGORY_REQUIRED(HttpStatus.BAD_REQUEST, "POST_CATEGORY_REQUIRED", "카테고리는 필수입니다."),
    POST_CONTENT_BLANK(HttpStatus.BAD_REQUEST, "POST_CONTENT_BLANK", "내용은 비어있을 수 없습니다."),
    POST_MEMBER_REQUIRED(HttpStatus.BAD_REQUEST, "POST_MEMBER_REQUIRED", "작성자 정보가 필요합니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_NOT_FOUND", "존재하지 않는 회원입니다."),
    MEMBER_NAME_BLANK(HttpStatus.BAD_REQUEST, "MEMBER_NAME_BLANK", "이름은 비어있을 수 없습니다."),
    MEMBER_NAME_TOO_LONG(HttpStatus.BAD_REQUEST, "MEMBER_NAME_TOO_LONG", "이름은 20자 이하여야 합니다."),
    MEMBER_EMAIL_BLANK(HttpStatus.BAD_REQUEST, "MEMBER_EMAIL_BLANK", "이메일은 비어있을 수 없습니다."),
    MEMBER_EMAIL_TOO_LONG(HttpStatus.BAD_REQUEST, "MEMBER_EMAIL_TOO_LONG", "이메일은 100자 이하여야 합니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "DUPLICATE_EMAIL", "이미 사용 중인 이메일입니다."),
    MEMBER_HAS_POSTS(HttpStatus.CONFLICT, "MEMBER_HAS_POSTS", "작성한 게시글이 있는 회원은 삭제할 수 없습니다."),
    INVALID_REQUEST_BODY(HttpStatus.BAD_REQUEST, "INVALID_REQUEST_BODY", "요청 본문의 형식이 올바르지 않습니다."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "INVALID_PARAMETER", "요청 파라미터의 형식이 올바르지 않습니다."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "RESOURCE_NOT_FOUND", "요청한 주소를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "METHOD_NOT_ALLOWED", "지원하지 않는 HTTP 메서드입니다."),
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "UNSUPPORTED_MEDIA_TYPE", "지원하지 않는 Content-Type입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "서버 오류가 발생했습니다.");

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(HttpStatus httpStatus, String code, String message) {
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