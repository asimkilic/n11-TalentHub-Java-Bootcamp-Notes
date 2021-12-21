package com.asimkilic.springboot.springboottraining.exception;

import java.util.Date;

public class ExceptionResponse {

    private Date errorDate;
    private String message;
    private String detail;

    public ExceptionResponse() {
    }

    public ExceptionResponse(Date errorDate, String message, String detail) {
        this.errorDate = errorDate;
        this.message = message;
        this.detail = detail;
    }

    public Date getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(Date errorDate) {
        this.errorDate = errorDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
