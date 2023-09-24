package com.gpay.gpay.exception;

public class ResponseException extends Exception{
    private static final long serialVersionUID = 1L;
    public ResponseException() {
        super();
    }

    public ResponseException(String message) {
        super(message);
    }
}
