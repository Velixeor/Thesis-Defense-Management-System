package ru.tubryansk.tdms.exception;

import ru.tubryansk.tdms.dto.ErrorResponse;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public ErrorResponse.ErrorCode getErrorCode() {
        return ErrorResponse.ErrorCode.INTERNAL_ERROR;
    }
}
