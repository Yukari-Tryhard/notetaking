package com.cnpmm.notetaking.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericResponse<T> {
    @JsonProperty("message")
    private String message;
    @JsonProperty("status-code")
    private Integer statusCode;
    @JsonProperty("data")
    private T data;

    public GenericResponse() {
        super();
    }

    public GenericResponse(String message, Integer statusCode, T data) {
        super();
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
    }

    public GenericResponse(ServiceResponse serviceResponse, T data) {
        super();
        this.message = serviceResponse.getMessage();
        this.statusCode = serviceResponse.getStatusCode();
        this.data = data;
    }
}

