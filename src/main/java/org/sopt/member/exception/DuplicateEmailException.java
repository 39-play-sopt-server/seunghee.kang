package org.sopt.member.exception;

import org.sopt.global.exception.BusinessException;
import org.sopt.global.response.ErrorCode;

public class DuplicateEmailException extends BusinessException {
    public DuplicateEmailException(ErrorCode errorCode) {
        super(errorCode);
    }
}