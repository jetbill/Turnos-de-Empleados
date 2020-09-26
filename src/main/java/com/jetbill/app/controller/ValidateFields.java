package com.jetbill.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class ValidateFields {
    public static ResponseEntity<?> validator(BindingResult result){
        Map<String, Object> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "el campo "+ err.getField()+" " +err.getDefaultMessage());
        });
        return new ResponseEntity<Map<String,Object>>(errors, HttpStatus.BAD_REQUEST);
    }
}
