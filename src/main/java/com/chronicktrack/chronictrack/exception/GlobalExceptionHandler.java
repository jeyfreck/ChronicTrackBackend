package com.chronicktrack.chronictrack.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex){
Map<String, String> errors = new HashMap<>();

ex.getBindingResult().getAllErrors().forEach(error ->{
String field = ((FieldError) error).getField();
String msg = error.getDefaultMessage();
errors.put(field, msg);
});
return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
}
}
