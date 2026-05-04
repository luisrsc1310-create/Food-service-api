package org.example.foodprojectjpa.API.ExceptionsHandlers;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handlerGeneric(Exception ex, HttpServletRequest request) {


        ErrorMessage error = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal server Error",
                Collections.singletonList(ex.getMessage()),
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> NotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {


        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        return new ResponseEntity<>(new ErrorMessage(
                400,
                "Bad Request",
                errors,
                request.getRequestURI()
        ), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> NotFoundHandler(ResourceNotFoundException ex, HttpServletRequest request) {

        ErrorMessage error = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                "Not found",
                Collections.singletonList(ex.getMessage()),
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(AlreadyExists.class)
    public ResponseEntity<ErrorMessage> AlreadyExistsHandler(AlreadyExists ex, HttpServletRequest request) {


        ErrorMessage error = new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                "The value is used",
                Collections.singletonList(ex.getMessage()),
                request.getRequestURI()


        );

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);

    }

}
