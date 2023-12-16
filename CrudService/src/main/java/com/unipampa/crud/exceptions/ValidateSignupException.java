package com.unipampa.crud.exceptions;

public class ValidateSignupException extends RuntimeException{
    private static final long serialVersionUID = -1380883678454941587L;

    public ValidateSignupException(String message) {
        super(message);
    }
}
