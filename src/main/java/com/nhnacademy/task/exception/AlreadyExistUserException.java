package com.nhnacademy.task.exception;

public class AlreadyExistUserException extends RuntimeException {
    public AlreadyExistUserException(String message) {
        super(message);
    }
}
