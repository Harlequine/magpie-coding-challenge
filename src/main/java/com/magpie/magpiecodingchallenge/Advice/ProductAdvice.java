package com.magpie.magpiecodingchallenge.Advice;


import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ProductAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)// Bad Request for input that did not pass validation bean
    @ExceptionHandler(MethodArgumentNotValidException.class)//exception to be thrown when validation on an arguement annotated with @Valid fails
    public ResponseEntity<Map<String, String>> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        String [] errorStatus = HttpStatus.BAD_REQUEST.toString().split(" ");
        errorMap.put("status", errorStatus[0]);
        errorMap.put("statusCode", errorStatus[1]);
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put("Message", error.getDefaultMessage());
        });


        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, String>> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        Map<String, String> errorMap = new HashMap<>();
        String [] errorStatus = HttpStatus.BAD_REQUEST.toString().split(" ");
        errorMap.put("status", errorStatus[0]);
        errorMap.put("statusCode", errorStatus[1]);
        errorMap.put("message", ex.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)//thrown when the request payload (body) is not readable. This can happen for various reasons
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, String> errorMap = new HashMap<>();
        String [] errorStatus = HttpStatus.BAD_REQUEST.toString().split(" ");
        errorMap.put("status", errorStatus[0]);
        errorMap.put("statusCode", errorStatus[1]);
        errorMap.put("message", "Check inputs");
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)//thrown when the request payload (body) is not readable. This can happen for various reasons
    public ResponseEntity<Map<String, String>> handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        String [] errorStatus = HttpStatus.BAD_REQUEST.toString().split(" ");
        errorMap.put("status", errorStatus[0]);
        errorMap.put("statusCode", errorStatus[1]);
        errorMap.put("message", "Product not found");
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

}
