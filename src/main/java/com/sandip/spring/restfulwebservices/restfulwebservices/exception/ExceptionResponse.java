package com.sandip.spring.restfulwebservices.restfulwebservices.exception;

import java.util.Date;

public class ExceptionResponse {
    private Date timeStamp;
    private String message;
    private String details;

    protected ExceptionResponse() {
    }

    public ExceptionResponse(Date timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
