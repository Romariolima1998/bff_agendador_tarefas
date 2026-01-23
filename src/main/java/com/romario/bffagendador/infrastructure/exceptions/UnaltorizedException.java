package com.romario.bffagendador.infrastructure.exceptions;


public class UnaltorizedException extends RuntimeException {

    public UnaltorizedException(String message) {
        super(message);
    }

    public UnaltorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
