package ru.ezhov.jpa.editor.configuration;

public class DbServiceException extends Exception {
    DbServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
