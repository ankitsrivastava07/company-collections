package com.company.collections.exceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.company.collections.dto.error.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.company.collections.response.ApiResponseDto;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static com.company.collections.utility.JobCollectionConstant.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger("");

    @ExceptionHandler(exception = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        ApiError apiError = new ApiError();

        List<FieldError> errors = exp.getFieldErrors();
        Map<String, String> fieldErr = new HashMap<>();
        errors
                .stream()
                .map(e -> {
                    fieldErr.put(e.getField(), e.getDefaultMessage());
                    return fieldErr;
                })
                .toList();

        apiError.setErrors(fieldErr);
        apiError.setStatus(Boolean.FALSE);
        apiError.setMessage("Validation Failed");
        log.info("Validation Failed {}", fieldErr);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> noResourceFoundExceptionHandle(NoResourceFoundException exception) {

        log.error(exception.getMessage(), exception);
        ApiError apiError = new ApiError();
        apiError.setStatus(Boolean.FALSE);
        apiError.setMessage(exception.getMessage());

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

/*    @ExceptionHandler(Exception.class)
    public ProblemDetail handleAllExceptions(Exception ex) {
        // Find the Neo4j ClientException anywhere in the hierarchy
        ClientException neo4jEx = findNeo4jException(ex);

        if (neo4jEx != null) {
            // Log the internal error (Node(6)...) strictly for your backend logs
            log.error("Neo4j Constraint Violation [{}]: {}", neo4jEx.code(), neo4jEx.getMessage());

            // Build a secure, user-friendly message
            String safeMessage = "A required field is missing or invalid.";

            // Internal check to provide a specific (but safe) message
            if (neo4jEx.getMessage().contains("property `name`")) {
                safeMessage = "The Company name is a required field.";
            }

            return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, safeMessage);
        }

        // Generic fallback for security
        log.error("Internal system error: ", ex);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
    }

    private ClientException findNeo4jException(Throwable t) {
        if (t == null) return null;
        if (t instanceof ClientException ce) return ce;

        // Also check suppressed exceptions (visible in your log)
        for (Throwable suppressed : t.getSuppressed()) {
            if (suppressed instanceof ClientException ce) return ce;
            ClientException foundInSuppressed = findNeo4jException(suppressed);
            if (foundInSuppressed != null) return foundInSuppressed;
        }

        return findNeo4jException(t.getCause());
    }*/

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        // 1. Log the full internal error for developers (Safe in logs, hidden from user)
        ApiError apiError = new ApiError();
        log.error("Database Integrity Violation: {}", ex.getMostSpecificCause().getMessage());
        String msg = ex.getMostSpecificCause().getMessage();
        if (msg.contains("Index entry conflict") &&
                msg.contains("already exists with label")
                && msg.contains("Company")) {
            apiError.setStatus(Boolean.FALSE);

            Pattern pattern = Pattern.compile("property\\s+`?(\\w+)`?\\s*=\\s*'(.*)'");
            Matcher matcher = pattern.matcher(msg);

            if (matcher.find()) {
                String field = matcher.group(1);
                String val = matcher.group(2).trim();
                // Constructing a specific but safe message
                String safeDetail = "The " + field + " '" + val + "' is already registered.";
                apiError.setMessage(safeDetail);
            }
            return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
        }

        apiError.setStatus(Boolean.FALSE);
        apiError.setMessage(INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException exp) {
        log.info("Method not supported ", exp);
        ApiError apiError = new ApiError();
        apiError.setMessage(exp.getMessage());
        apiError.setStatus(Boolean.FALSE);
        return new ResponseEntity<>(apiError, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException exp) {
        log.info("Http message not readable {}", exp.getLocalizedMessage());
        ApiError apiError = new ApiError();
        apiError.setMessage("Malformed JSON request");
        apiError.setStatus(Boolean.FALSE);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleException(Throwable exp) {
        log.info("Exception has been occurred ", exp);
        exp.fillInStackTrace();
        ApiError apiError = new ApiError();

        if (exp instanceof TransactionSystemException) {
            apiError.setMessage(INTERNAL_SERVER_ERROR);
        }

        apiError.setMessage("Something went wrong, please contact support team");
        apiError.setStatus(Boolean.FALSE);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
