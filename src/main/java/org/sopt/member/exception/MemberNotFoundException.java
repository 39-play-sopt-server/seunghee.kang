package org.sopt.member.exception;

import org.sopt.global.exception.BusinessException;
import org.sopt.global.response.ErrorCode;

public class MemberNotFoundException extends BusinessException {
    public MemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}