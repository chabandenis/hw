package ru.otus.hw.ex12sd.exception;

public class ServiceException extends Exception {
    private ErrorCode errorCode;

    public ServiceException () {
        super();
    }

    public ServiceException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ServiceException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}

