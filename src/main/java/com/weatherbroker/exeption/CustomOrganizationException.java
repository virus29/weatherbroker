package com.weatherbroker.exeption;

public class CustomOrganizationException extends Exception {
    public CustomOrganizationException(String message) {
        super(message);
    }

    public CustomOrganizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
