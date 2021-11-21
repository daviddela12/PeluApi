package com.daviddela.peluapi.exception;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LogManager.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request, BindingResult results) {
        List<String> errors = new ArrayList<String>();
        for ( ObjectError error : results.getFieldErrors()) {
            errors.add(error.getObjectName()+": "+error.getDefaultMessage());
        }
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                errors,
                request.getDescription(false));
        logger.error(message);
        return message;
    }

    @ExceptionHandler(Exception.class)
    public ErrorMessage generalError(Exception ex, WebRequest request, BindingResult results) {
        List<String> errors = new ArrayList<String>();
        for ( ObjectError error : results.getFieldErrors()) {
            errors.add(error.getObjectName()+": "+error.getDefaultMessage());
        }
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                errors,
                request.getDescription(false));
        logger.error(message);
        return message;
    }
}
