package com.bridgelabz.quantitymeasurement.response;

import org.springframework.http.HttpStatus;

public class Response {
    private Object value;
    private HttpStatus status;

    public Response(Object value, HttpStatus status) {
        this.value = value;
        this.status = status;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
