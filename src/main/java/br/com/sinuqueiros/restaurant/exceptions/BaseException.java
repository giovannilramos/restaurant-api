package br.com.sinuqueiros.restaurant.exceptions;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {
    private final Integer code;
    protected BaseException(final Integer code, final String message) {
        super(message);
        this.code = code;
    }
}
