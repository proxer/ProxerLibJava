package com.proxerme.library.util;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.UtilityClass;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
@UtilityClass
@Accessors(fluent = true)
public final class ProxerUrls {

    @Getter(onMethod = @__({@NotNull}))
    private final HttpUrl apiBase = new HttpUrl.Builder()
            .scheme("https")
            .host("proxer.me")
            .addPathSegment("api")
            .addPathSegment("v1")
            .addPathSegment("")
            .build();
}
