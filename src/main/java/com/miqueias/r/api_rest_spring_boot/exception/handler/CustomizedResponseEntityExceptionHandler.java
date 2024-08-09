package com.miqueias.r.api_rest_spring_boot.exception.handler;

import com.miqueias.r.api_rest_spring_boot.exception.*;
import com.miqueias.r.api_rest_spring_boot.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Response<Void>> handleAllExceptions(Exception ex, WebRequest request) {
        Response<Void> exceptionResponse = new Response<>(
                "error",
                ex.getMessage(),
                null,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getDescription(false),
                new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Response<Void>> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        Response<Void> exceptionResponse = new Response<>(
                "error",
                ex.getMessage(),
                null,
                HttpStatus.NOT_FOUND.value(),
                request.getDescription(false),
                new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CpfAlreadyInUseException.class)
    public final ResponseEntity<Response<Void>> handleCpfAlreadyInUseException(CpfAlreadyInUseException ex, WebRequest request) {
        Response<Void> exceptionResponse = new Response<>(
                "error",
                ex.getMessage(),
                null,
                HttpStatus.CONFLICT.value(),
                request.getDescription(false),
                new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CpfUpdateNotAllowedException.class)
    public final ResponseEntity<Response<Void>> handleCpfUpdateNotAllowedException(CpfUpdateNotAllowedException ex, WebRequest request) {
        Response<Void> exceptionResponse = new Response<>(
                "error",
                ex.getMessage(),
                null,
                HttpStatus.FORBIDDEN.value(),
                request.getDescription(false),
                new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public final ResponseEntity<Response<Void>> handleEmailAlreadyInUseException(EmailAlreadyInUseException ex, WebRequest request) {
        Response<Void> exceptionResponse = new Response<>(
                "error",
                ex.getMessage(),
                null,
                HttpStatus.CONFLICT.value(),
                request.getDescription(false),
                new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EndpointException.class)
    public final ResponseEntity<Response<Void>> handleEndpointExpiredException(EndpointException ex, WebRequest request) {
        Response<Void> exceptionResponse = new Response<>(
                "error",
                ex.getMessage(),
                null,
                HttpStatus.FORBIDDEN.value(),
                request.getDescription(false),
                new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public final ResponseEntity<Response<Void>> handleInvalidJwtAuthenticationException(InvalidJwtAuthenticationException ex, WebRequest request) {
        Response<Void> exceptionResponse = new Response<>(
                "error",
                ex.getMessage(),
                null,
                HttpStatus.FORBIDDEN.value(),
                request.getDescription(false),
                new Date()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }
}
