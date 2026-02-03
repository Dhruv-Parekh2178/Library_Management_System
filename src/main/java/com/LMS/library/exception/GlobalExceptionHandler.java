package com.LMS.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.View;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final View error;

    public GlobalExceptionHandler(View error) {
        this.error = error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgsExp(MethodArgumentNotValidException ex , Model model) {
        Map<String, String> response = new HashMap<>();

        ex.getBindingResult().getAllErrors()
                .forEach(err -> {
                    String fieldName = ((FieldError) err).getField();
                    String message = err.getDefaultMessage();
                    response.put(fieldName, message);
                });


        model.addAttribute("errorMessage", response.toString());
        return "error_ex";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String myResourseNotFound(ResourceNotFoundException ex , Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error_404";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex , Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error_ex";

    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex , Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error_ex";

    }
}