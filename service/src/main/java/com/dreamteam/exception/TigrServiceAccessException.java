package com.dreamteam.exception;

import org.springframework.dao.DataAccessException;

/**
 *  Exception for failures in service layer
 * 
 * @author Jiri Oliva
 */
public class TigrServiceAccessException extends DataAccessException {
    public TigrServiceAccessException(String msg) {
        super(msg);
    }

    public TigrServiceAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }  
}
