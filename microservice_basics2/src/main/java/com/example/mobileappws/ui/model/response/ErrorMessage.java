package com.example.mobileappws.ui.model.response;

import java.util.Date;

public class ErrorMessage {
    private Date timeStamp;
    private String errorMessage;

    public ErrorMessage() {
    }

    public ErrorMessage(Date timeStamp, String errorMessage) {
        this.timeStamp = timeStamp;
        this.errorMessage = errorMessage;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
