package com.jamie.Task.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class TaskAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;
}
