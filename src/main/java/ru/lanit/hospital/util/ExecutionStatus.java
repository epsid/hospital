package ru.lanit.hospital.util;

import ru.lanit.hospital.model.User;

public class ExecutionStatus {
    private User user;
    private String code;
    private String message;

    public ExecutionStatus() {
    }

    public ExecutionStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
