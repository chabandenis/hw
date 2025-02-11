package ru.otus.hw.ex12sd.secutiry.exception;

import org.springframework.security.core.AuthenticationException;

public class ExpiredTokenException extends AuthenticationException {

    public ExpiredTokenException(final String token, final String msg, final Throwable t) {
        super(msg, t);
    }
}
