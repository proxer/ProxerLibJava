package com.proxerme.library.api;

import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public final class ProxerResponse<T> {

    @Json(name = "error")
    private int error;
    @Json(name = "message")
    private String message;
    @Json(name = "code")
    private int code;
    @Json(name = "data")
    private T data;

    public boolean isSuccessful() {
        return error == 0;
    }

    @NotNull
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }
}
