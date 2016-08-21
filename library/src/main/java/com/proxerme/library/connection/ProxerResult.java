package com.proxerme.library.connection;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public abstract class ProxerResult<T> {

    private int error;
    private String message;
    private int code;

    boolean isSuccess() {
        return error == 0;
    }

    String getMessage() {
        return message;
    }

    int getCode() {
        return code;
    }

    public abstract T getData();

}
