package com.jetbill.app.exeptions;

public class EmpleadoExeption extends RuntimeException {
    public EmpleadoExeption(String message) {
        super(message);
    }

    public EmpleadoExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
