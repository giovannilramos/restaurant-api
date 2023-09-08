package br.com.sinuqueiros.restaurant.exceptions;

import static br.com.sinuqueiros.restaurant.enums.StatusCodeEnum.BAD_REQUEST;

public class BadRequestException extends BaseException {
    public BadRequestException(final String msg) {
        super(BAD_REQUEST.getStatusCodeNumber(), msg);
    }
}
