package com.dreamteam.exceptions;

/**
 * Created by jan.novak
 */
public class TigrAPIException extends RuntimeException {

    public TigrAPIException() {
    }

    public TigrAPIException(String s) {
        super(s);
    }

    public TigrAPIException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public TigrAPIException(Throwable throwable) {
        super(throwable);
    }
}
