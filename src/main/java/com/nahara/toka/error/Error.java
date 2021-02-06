package com.nahara.toka.error;

import java.util.Date;


public class Error {

    private Date date;
    private String status;
    private String message;
    private String details;


    public Error(Date date, String status, String message, String details) {
        this.date = date;
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
