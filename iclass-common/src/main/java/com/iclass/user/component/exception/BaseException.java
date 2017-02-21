package com.iclass.user.component.exception;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/20/2017 11:49 PM.
 */
public class BaseException extends RuntimeException{

    private String message;

    private String reason;

    public BaseException(String message, String reason) {
        super();
        this.message = message;
        this.reason = reason;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
