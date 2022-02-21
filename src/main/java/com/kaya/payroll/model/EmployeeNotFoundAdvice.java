package com.kaya.payroll.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmployeeNotFoundAdvice {

    @ResponseBody   // advice should be rendered straight into the response body
    @ExceptionHandler(EmployeeNotFoundException.class)  // matches this handler to the given exception
    @ResponseStatus(HttpStatus.NOT_FOUND)   // issues an HTTP 404 error
    public String employeeNotFoundHandler(EmployeeNotFoundException e) {
        return e.getMessage();  // response body
    }

}
