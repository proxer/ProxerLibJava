package me.proxer.library.api;

import com.squareup.moshi.Json;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.Nullable;

/**
 * @author Ruben Gees
 */
@SuppressWarnings("TypeParameterUnusedInFormals")
@ToString
@EqualsAndHashCode(onParam = @__({@Nullable}))
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

    @Nullable
    @SuppressWarnings("unused")
    @Getter(AccessLevel.PACKAGE)
    @Json(name = "data")
    private T data;

    boolean isSuccessful() {
        return !error;
    }
}
