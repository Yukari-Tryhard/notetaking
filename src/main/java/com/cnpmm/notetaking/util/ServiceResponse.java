package com.cnpmm.notetaking.util;

public class ServiceResponse {
    private Integer statusCode;
    private String message;

    public ServiceResponse(String message) {
        super();
        this.message = message;
    }

    public ServiceResponse(Integer statusCode, String message) {
        super();
        this.statusCode = statusCode;
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
