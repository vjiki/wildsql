package com.github.vjiki.wildsql.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public final class MyException extends RuntimeException {

    public MyException() {
        super();
    }

    public MyException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MyException(final String message) {
        super(message);
    }

    public MyException(final Throwable cause) {
        super(cause);
    }
}
