package com.vpukas.postit.exception;


import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 This class defines an exception handler for method argument validation errors.
 It extends the ResponseEntityExceptionHandler class from Spring and is annotated with @ControllerAdvice.
 It overrides the handleMethodArgumentNotValid method to handle MethodArgumentNotValidException and return a response with an appropriate body and status code.
 */

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * This method handles MethodArgumentNotValidException by extracting the validation errors from the binding result and
     * returning a response with a map containing the errors and a 400 bad request status code.
     *
     * @param ex      the MethodArgumentNotValidException to handle
     * @param headers the HTTP headers to include in the response
     * @param status  the HTTP status code to include in the response
     * @param request the web request that resulted in the exception
     * @return a ResponseEntity containing the error map and HTTP status code
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}

