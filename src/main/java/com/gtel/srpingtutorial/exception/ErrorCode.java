package com.gtel.srpingtutorial.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {

    AIRPORT_EXISTED(1001, "AIRPORT EXISTED", HttpStatus.BAD_REQUEST),
    AIRPORT_NOT_EXISTED(1003, "AIRPORT NOT FOUND", HttpStatus.NOT_FOUND);

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
