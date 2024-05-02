package com.cache.distributed.exception;

public class NoStudentFoundException extends RuntimeException{
    public NoStudentFoundException(String msg) {
        super(msg);
    }
}
