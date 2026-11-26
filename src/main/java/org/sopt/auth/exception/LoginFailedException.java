package org.sopt.auth.exception;

import org.sopt.global.exception.BusinessException;
import org.sopt.global.response.ErrorCode;

public class LoginFailedException extends BusinessException {
    public LoginFailedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
