package com.nhnacademy.task.common.advice;

import com.nhnacademy.task.exception.AlreadyExistMemberException;
import com.nhnacademy.task.exception.NotFoundMemberException;
import com.nhnacademy.task.exception.WithdrawalMemberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebRestControllerAdvice {

    @ExceptionHandler(AlreadyExistMemberException.class)
    public ResponseEntity<String> alreadyExistUserException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundMemberException.class)
    public ResponseEntity<String> notFoundUserException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder("유효성 검사 실패: ");
        ex.getBindingResult().getAllErrors().forEach(error ->
                errorMessage.append(error.getDefaultMessage()).append(" ")
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
    }

    @ExceptionHandler(WithdrawalMemberException.class)
    public ResponseEntity<String> withdrawalMemberException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(ex.getMessage());
    }


}
