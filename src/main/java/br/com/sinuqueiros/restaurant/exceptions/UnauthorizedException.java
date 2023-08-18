package br.com.sinuqueiros.restaurant.exceptions;

import br.com.sinuqueiros.restaurant.enums.StatusCodeEnum;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException(final String message) {
        super(StatusCodeEnum.UNAUTHORIZED.getStatusCodeNumber(), message);
    }
}
