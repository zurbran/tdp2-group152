package com.tdp2.group152.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceNotAvailableException extends RuntimeException {

    public ResourceNotAvailableException() {
        super();
    }

    public ResourceNotAvailableException(String message) {
        super(message);
    }
}
