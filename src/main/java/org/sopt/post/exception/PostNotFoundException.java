package org.sopt.post.exception;

import org.sopt.global.exception.BusinessException;
import org.sopt.global.response.ErrorCode;

public class PostNotFoundException extends BusinessException {
    public PostNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}