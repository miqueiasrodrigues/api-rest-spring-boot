package com.miqueias.r.api_rest_spring_boot.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.CONFLICT)
public class CpfUpdateNotAllowedException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public CpfUpdateNotAllowedException(String message) {
        super(message);
    }
}
