package com.fpt.rentahome.Helpers;

public class AuthResponse {
    private String accessToken;

    private boolean success;

    private String message;

    public AuthResponse(boolean success, String message, String accessToken) {
        this.accessToken = accessToken;
        this.success = success;
        this.message = message;
    }

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
