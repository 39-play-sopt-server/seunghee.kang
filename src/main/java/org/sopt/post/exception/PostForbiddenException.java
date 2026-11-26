package org.sopt.post.exception;

import org.sopt.global.exception.BusinessException;
import org.sopt.global.response.ErrorCode;

public class PostForbiddenException extends BusinessException {
    public PostForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
