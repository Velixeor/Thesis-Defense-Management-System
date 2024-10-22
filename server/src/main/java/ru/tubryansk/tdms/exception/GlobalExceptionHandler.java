package ru.tubryansk.tdms.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.classfile.Unknown;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import ru.tubryansk.tdms.dto.ErrorResponse;

import java.net.UnknownHostException;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // todo: make a better error message
        return new ErrorResponse(e.getMessage(), ErrorResponse.ErrorCode.VALIDATION_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ErrorResponse handleBusinessException(BusinessException e, HttpServletResponse response) {
        response.setStatus(e.getErrorCode().getHttpStatus().value());
        return new ErrorResponse(e.getMessage(), e.getErrorCode());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoResourceFoundException(NoResourceFoundException e) {
        // todo: make error page
        return new ErrorResponse(e.getMessage(), ErrorResponse.ErrorCode.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnexpectedException(Exception e) {
        // todo: make error page
        log.error("Unexpected exception.", e);
        return new ErrorResponse(e.getMessage(), ErrorResponse.ErrorCode.INTERNAL_ERROR);
    }
}
