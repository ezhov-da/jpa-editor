package ru.ezhov.jpa.editor.h2db;

public class H2DbServiceException extends Exception {
    H2DbServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
