package org.vojin.meetingscheduler.exception;

import java.util.ArrayList;
import java.util.Date;

public class ErrorDetails {

    private Date timestamp;
    private String message;
    private ArrayList<String> details;

    public ErrorDetails(Date timestamp, String message, ArrayList<String> details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<String> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<String> details) {
        this.details = details;
    }

}
