package org.sopt.global.response;

public enum ResponseCode {

    OK(200, "게시글 조회 성공"),
    CREATED(201, "정상적으로 생성되었습니다."),
    UPDATED(200, "게시글 수정 성공"),
    DELETED(200, "게시글 삭제 성공"),

    BAD_REQUEST(400, "잘못된 요청입니다."),
    INVALID_CATEGORY(400, "올바르지 않은 카테고리입니다."),
    POST_NOT_FOUND(404, "게시글이 없습니다."),
    NOT_FOUND(404, "게시글을 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "서버 오류가 발생했습니다.");

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}