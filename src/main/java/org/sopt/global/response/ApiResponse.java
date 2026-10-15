package org.sopt.global.response;

public class ApiResponse<T> {

    private int code;
    private String message;
    private T data;

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> ApiResponse<T> success(ResponseCode responseCode, T data) {
        return new ApiResponse<>(
                responseCode.getCode(),
                responseCode.getMessage(),
                data
        );
    }

    public static ApiResponse<Void> success(ResponseCode responseCode) {
        return new ApiResponse<>(
                responseCode.getCode(),
                responseCode.getMessage(),
                null
        );
    }

    public static <T> ApiResponse<T> fail(ResponseCode responseCode) {
        return new ApiResponse<>(
                responseCode.getCode(),
                responseCode.getMessage(),
                null
        );
    }

    public static <T> ApiResponse<T> fail(ResponseCode responseCode, String message) {
        return new ApiResponse<>(
                responseCode.getCode(),
                message,
                null
        );
    }


}
