package ru.tubryansk.tdms.exception;

import ru.tubryansk.tdms.dto.ErrorResponse;

public class AccessDeniedException extends BusinessException {
    public AccessDeniedException() {
        super("Access denied");
    }

    @Override
    public ErrorResponse.ErrorCode getErrorCode() {
        return ErrorResponse.ErrorCode.ACCESS_DENIED;
    }
}
