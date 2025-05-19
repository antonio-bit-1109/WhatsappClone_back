package com.example.demo.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse {

    @Schema(description = "the path invoked by client that generate the error")
    private String apiPath;

    @Schema(description = "the status code released by the error")
    private HttpStatus httpStatus;

    @Schema(description = "the error message")
    private String errMsg;

    @Schema(description = "time when the error happened")
    private LocalDateTime timeError;

    public ErrorResponse(String apiPath, HttpStatus httpStatus, String errMsg, LocalDateTime timeError) {
        this.apiPath = apiPath;
        this.httpStatus = httpStatus;
        this.errMsg = errMsg;
        this.timeError = timeError;
    }

    public ErrorResponse() {
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public LocalDateTime getTimeError() {
        return timeError;
    }

    public void setTimeError(LocalDateTime timeError) {
        this.timeError = timeError;
    }
}
