package com.nhnacademy.task.exception;

public class AlreadyExistMemberException extends RuntimeException {
    public AlreadyExistMemberException(String message) {
        super(message);
    }
}
