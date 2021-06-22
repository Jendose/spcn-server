package com.spcn.spcnservices.spcnapi.advices;

import com.spcn.spcnservices.spcnapi.exceptions.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @Data
    @AllArgsConstructor
    private static class ErrorResponse {
        private String error;

        public ErrorResponse() {
        }
    }

    @ExceptionHandler({UserAlreadyExistsException.class, UserNotFoundException.class, InvalidUserPasswordException.class, IncorrectUserEmailException.class, SpcNotFoundException.class, UnexpectedException.class})
    public ErrorResponse illegalArgumentHandler(RuntimeException e) {
        return new ErrorResponse(e.getMessage());
    }

}
