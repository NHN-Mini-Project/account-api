package com.nhnacademy.task.common.advice;

import com.nhnacademy.task.exception.AlreadyExistUserException;
import com.nhnacademy.task.exception.NotFoundUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebRestControllerAdvice {

    @ExceptionHandler(AlreadyExistUserException.class)
    public ResponseEntity<String> alreadyExistUserException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<String> notFoundUserException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
