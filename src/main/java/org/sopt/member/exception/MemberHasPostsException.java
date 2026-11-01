package org.sopt.member.exception;

import org.sopt.global.exception.BusinessException;
import org.sopt.global.response.ErrorCode;

public class MemberHasPostsException extends BusinessException {
    public MemberHasPostsException(ErrorCode errorCode) {
        super(errorCode);
    }
}