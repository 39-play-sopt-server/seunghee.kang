package org.sopt.global.jwt;

import org.sopt.global.exception.BusinessException;
import org.sopt.global.response.ErrorCode;

public class InvalidTokenException extends BusinessException {
    public InvalidTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
