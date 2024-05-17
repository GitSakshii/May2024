package com.example.ECommerce.Platform.config;

import com.example.ECommerce.Platform.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private HttpStatus status;
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handlesValidationErrors(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        Map<String,Object>responseData=new HashMap<>();
        responseData.put("message",errors);
        status= HttpStatus.BAD_REQUEST;
        responseData.put("Success",false);
        return ResponseEntity.status(status).body(responseData);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String,Object>>handlesProductNotFoundException(Exception exception){
        Map<String ,Object> responseData = new HashMap<>();
        responseData.put("message", exception.getMessage());
        status=HttpStatus.NOT_FOUND;
        responseData.put("Success",false);
        return ResponseEntity.status(status).body(responseData);

    }
}
