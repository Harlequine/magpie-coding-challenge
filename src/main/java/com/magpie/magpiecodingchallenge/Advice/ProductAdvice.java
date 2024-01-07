package com.magpie.magpiecodingchallenge.Advice;


import com.magpie.magpiecodingchallenge.Exception.ProductException;
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        String [] errorStatus = HttpStatus.BAD_REQUEST.toString().split(" ");
        errorMap.put("status", errorStatus[0]);
        errorMap.put("statusCode", errorStatus[1]);
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put("message", error.getDefaultMessage());
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
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, String> errorMap = new HashMap<>();
        String [] errorStatus = HttpStatus.BAD_REQUEST.toString().split(" ");
        errorMap.put("status", errorStatus[0]);
        errorMap.put("statusCode", errorStatus[1]);
        errorMap.put("message", "Check input.");
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ProductException.class)//Custom error handling, mainly used for handling businesslogic errors
    public ResponseEntity<Map<String, String>> handleBusinessException(ProductException ex) {
        Map<String, String> errorMap = new HashMap<>();
        String [] errorStatus = HttpStatus.BAD_REQUEST.toString().split(" ");
        errorMap.put("status", errorStatus[0]);
        errorMap.put("statusCode", errorStatus[1]);
        errorMap.put("message", ex.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
