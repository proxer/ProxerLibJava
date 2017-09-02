package me.proxer.library.api;

import com.squareup.moshi.Json;
import lombok.*;

/**
 * @author Ruben Gees
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class ProxerResponse<T> {

    @SuppressWarnings("unused")
    @Getter(AccessLevel.NONE)
    @Json(name = "error")
    private boolean error;

    @SuppressWarnings("unused")
    @Getter(AccessLevel.PACKAGE)
    @Json(name = "message")
    private String message;

    @SuppressWarnings("unused")
    @Getter(AccessLevel.PACKAGE)
    @Json(name = "code")
    private int code;

    @SuppressWarnings("unused")
    @Getter(AccessLevel.PACKAGE)
    @Json(name = "data")
    private T data;

    boolean isSuccessful() {
        return !error;
    }
}
