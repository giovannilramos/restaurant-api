package br.com.sinuqueiros.restaurant.exceptions;

import static br.com.sinuqueiros.restaurant.enums.StatusCodeEnum.ALREADY_EXISTS;

public class ConflictException extends BaseException {
    public ConflictException(final String msg) {
        super(ALREADY_EXISTS.getStatusCodeNumber(), msg);
    }
}
