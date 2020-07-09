package com.bridgelabz.quantitymeasurement.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

@ApiModel(description = "contains response from server",value = "Response")
public class Response {
    @ApiModelProperty(name = "value", value = "contains response value from server")
    private Object value;
    @ApiModelProperty(name = "status", value = "Represents response status")
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
