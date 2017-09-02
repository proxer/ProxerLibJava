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

    @Getter(AccessLevel.NONE)
    @Json(name = "error")
    boolean error;

    @Getter(AccessLevel.PACKAGE)
    @Json(name = "message")
    String message;

    @Getter(AccessLevel.PACKAGE)
    @Json(name = "code")
    int code;

    @Getter(AccessLevel.PACKAGE)
    @Json(name = "data")
    T data;

    boolean isSuccessful() {
        return !error;
    }
}
