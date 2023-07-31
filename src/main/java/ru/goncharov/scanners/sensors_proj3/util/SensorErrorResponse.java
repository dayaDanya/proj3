package ru.goncharov.scanners.sensors_proj3.util;

public class SensorErrorResponse {
    private String message;
    private long timestamp;



    public SensorErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

