package com.tdp2.group152.exceptions;

public class ApiErrorModel {
    private int status;
    private String error;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ApiErrorModel(int status, String error) {
        this.status = status;
        this.error = error;
    }
}
