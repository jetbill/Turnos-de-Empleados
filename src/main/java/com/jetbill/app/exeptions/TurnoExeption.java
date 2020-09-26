package com.jetbill.app.exeptions;

public class TurnoExeption  extends RuntimeException{
    public TurnoExeption(String message) {
        super(message);
    }

    public TurnoExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
