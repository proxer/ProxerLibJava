package com.proxerme.library.util;

import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public final class ProxerUrls {

    private static String HOST = "proxer.me";

    private static final HttpUrl API_BASE = new HttpUrl.Builder()
            .scheme("https")
            .host(HOST)
            .addPathSegment("api")
            .addPathSegment("v1")
            .addPathSegment("")
            .build();

    private ProxerUrls() {

    }

    @NotNull
    public static HttpUrl apiBase() {
        return API_BASE;
    }
}
