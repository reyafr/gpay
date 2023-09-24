package com.gpay.gpay.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class GenericResponseDTO<T> implements Serializable {
    private ResponseStatus status;
    private int code;
    private String message;
    private T data;

    @JsonIgnore
    public GenericResponseDTO<T> successResponse() {
        GenericResponseDTO<T> data = new GenericResponseDTO();
        data.setStatus(ResponseStatus.S);
        data.setCode(201);
        data.setMessage("Process Successed");
        return data;
    }

    @JsonIgnore
    public GenericResponseDTO<T> successResponse(T t) {
        GenericResponseDTO<T> data = new GenericResponseDTO();
        data.setStatus(ResponseStatus.S);
        data.setCode(201);
        data.setData(t);
        data.setMessage("Process Successed");
        return data;
    }

    @JsonIgnore
    public GenericResponseDTO<T> noDataFoundResponse(T t) {
        GenericResponseDTO<T> data = new GenericResponseDTO();
        data.setStatus(ResponseStatus.S);
        data.setCode(204);
        data.setData(t);
        data.setMessage("No Data Found");
        return data;
    }

    @JsonIgnore
    public GenericResponseDTO<T> noDataFoundResponse() {
        GenericResponseDTO<T> data = new GenericResponseDTO();
        data.setStatus(ResponseStatus.S);
        data.setCode(204);
        data.setMessage("No Data Found");
        return data;
    }

    @JsonIgnore
    public GenericResponseDTO<T> errorResponse(int code, String message) {
        GenericResponseDTO<T> data = new GenericResponseDTO();
        data.setStatus(ResponseStatus.F);
        data.setCode(code);
        data.setMessage(message);
        return data;
    }

    @JsonIgnore
    public GenericResponseDTO<T> errorResponse(int code, String message, T t) {
        GenericResponseDTO<T> data = new GenericResponseDTO();
        data.setStatus(ResponseStatus.F);
        data.setCode(code);
        data.setData(t);
        data.setMessage(message);
        return data;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseStatus getStatus() {
        return this.status;
    }


    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public GenericResponseDTO() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public GenericResponseDTO(ResponseStatus status, int code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
