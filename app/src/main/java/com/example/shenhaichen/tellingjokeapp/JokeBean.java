package com.example.shenhaichen.tellingjokeapp;

/**
 * Created by shenhaichen on 29/11/2017.
 */

public class JokeBean {
    String message;
    boolean error;

    public JokeBean() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
