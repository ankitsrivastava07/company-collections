package com.company.collections.exceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.neo4j.driver.exceptions.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.company.collections.response.ApiResponseDto;
import com.company.collections.utility.JobCollectionConstant;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static com.company.collections.utility.JobCollectionConstant.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger("");

    @ExceptionHandler(exception = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        ApiResponseDto apiResponse = new ApiResponseDto();

        List<FieldError> errors = exp.getFieldErrors();
        Map<String, String> error = errors.stream().collect(Collectors.toMap(e -> e.getField(), e -> e.getDefaultMessage()));

        apiResponse.setError(error);
        apiResponse.setStatus(Boolean.FALSE);
        apiResponse.setMsg("Validation Failed");
        log.info("Validation Failed {}", error);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> noResourceFoundExceptionHandle(NoResourceFoundException exception) {

        exception.printStackTrace();
        ApiResponseDto apiResponse = new ApiResponseDto();
        apiResponse.setStatus(Boolean.FALSE);
        apiResponse.setMsg(exception.getMessage());

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

/*
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleIllegalStateException(IllegalStateException exp) {
        ApiResponseDto api = new ApiResponseDto();
        if (exp.getCause() instanceof ClientException cltExp) {

            api.setStatus(Boolean.FALSE);
            api.setMsg("Company name field value is missing");
            return new ResponseEntity<>(api, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
*/

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
        ApiResponseDto apiResponse = new ApiResponseDto();
        log.error("Database Integrity Violation: {}", ex.getMostSpecificCause().getMessage());
        String msg = ex.getMessage();
        if (msg.contains("already exists") && msg.contains("company")) {
            apiResponse.setStatus(Boolean.FALSE);
            apiResponse.setMsg("Company with same already exists in our records");
            return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
        }

        apiResponse.setStatus(Boolean.FALSE);
        apiResponse.setMsg(INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleException(Throwable exp) {
        log.info("Exception has been occurred ", exp);
        exp.fillInStackTrace();
        ApiResponseDto apiResponse = new ApiResponseDto();

        if (exp instanceof TransactionSystemException) {
            apiResponse.setMsg(INTERNAL_SERVER_ERROR);
        }

        apiResponse.setStatus(JobCollectionConstant.FLASE);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
