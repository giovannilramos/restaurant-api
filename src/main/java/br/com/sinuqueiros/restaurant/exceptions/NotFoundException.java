package br.com.sinuqueiros.restaurant.exceptions;

import static br.com.sinuqueiros.restaurant.enums.StatusCodeEnum.NOT_FOUND;

public class NotFoundException extends BaseException {
    public NotFoundException(final String msg) {
        super(NOT_FOUND.getStatusCodeNumber(), msg);
    }
}
