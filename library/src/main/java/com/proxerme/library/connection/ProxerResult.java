package com.proxerme.library.connection;

import com.squareup.moshi.Json;

/**
 * A container for the result. This class is only used internally und should not be used by the user
 * of this lib.
 *
 * @author Ruben Gees
 */
public abstract class ProxerResult<T> {

    @Json(name = "error")
    private int error;
    @Json(name = "message")
    private String message;
    @Json(name = "code")
    private int code;

    public boolean isSuccess() {
        return error == 0;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public abstract T getData();

}
