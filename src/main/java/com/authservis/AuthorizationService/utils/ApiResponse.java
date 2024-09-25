package com.authservis.AuthorizationService.utils;

import org.springframework.http.HttpStatus;


public class ApiResponse {
    private String message;
    private HttpStatus status;
    private Integer code;

    public ApiResponse(String message, HttpStatus status, Integer code) {
        this.message = message;
        this.status = status;
        this.code = code;
    }


    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ApiResponse() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
