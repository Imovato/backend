package com.unipampa.crud.exceptions;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -5528826721501929445L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
