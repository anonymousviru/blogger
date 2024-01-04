package com.blog.Blogger.payload;

import lombok.Data;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String mess;
    private String details;

    public ErrorDetails(Date timestamp, String mess, String details) {
        this.timestamp = timestamp;
        this.mess = mess;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMess() {
        return mess;
    }

    public String getDetails() {
        return details;
    }
}
