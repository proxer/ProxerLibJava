package com.proxerme.library.entitiy;

import com.squareup.moshi.Json;
import lombok.*;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProxerResponse<T> {

    @Getter(AccessLevel.NONE)
    @Json(name = "error")
    private boolean error;

    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "message")
    private String message;

    @Json(name = "code")
    private int code;

    @Json(name = "data")
    private T data;

    public boolean isSuccessful() {
        return !error;
    }
}
