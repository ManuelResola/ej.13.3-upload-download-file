package com.example.ej13uploaddownloadfile.exceptions;

import com.example.ej13uploaddownloadfile.entity.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
@ResponseBody
public class ExceptionController {

    @ExceptionHandler(FileNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomError handleFileNotFoundException(FileNotFound exception) {
        return new CustomError(exception.getMessage(), 404L, LocalDateTime.now());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public CustomError handleRunTimeException(RuntimeException exception) {
        return new CustomError(exception.getMessage(), 406L, LocalDateTime.now());
    }
}