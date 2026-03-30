package com.goros._9_keo_vuththana_springboot_homework003.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException (NotFoundException ex, HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        problemDetail.setDetail(ex.getMessage());
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setStatus(404);
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setType(URI.create(request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort() + request.getContextPath() +  "/errors/not-found"));
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        problemDetail.setTitle("Bad Request");
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        problemDetail.setTitle("Bad Request");
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            errors.put(violation.getPropertyPath().toString() ,violation.getMessage());
        });

        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }

    @ExceptionHandler(EmailExistException.class)
    public ProblemDetail handleEmailExistException(EmailExistException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        problemDetail.setDetail(ex.getMessage());
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setStatus(400);
        problemDetail.setTitle("Conflict");
        problemDetail.setType(URI.create(request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort() + request.getContextPath() +  "/errors/not-found"));
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }
}
