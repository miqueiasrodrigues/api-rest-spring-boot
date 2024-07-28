package com.miqueias.r.api_rest_spring_boot.utils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Response<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String status;
    private Integer statusCode;
    private String message;
    private T data;
    private String details;
    private Date timestamp;


    public Response(String status, String message, T data, Integer statusCode, String details, Date timestamp) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
        this.details = details;
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response<?> response = (Response<?>) o;
        return Objects.equals(status, response.status) && Objects.equals(statusCode, response.statusCode) && Objects.equals(message, response.message) && Objects.equals(data, response.data) && Objects.equals(details, response.details) && Objects.equals(timestamp, response.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, statusCode, message, data, details, timestamp);
    }
}
