package me.proxer.library.api;

import com.squareup.moshi.FromJson;
import okhttp3.HttpUrl;

/**
 * @author Ruben Gees
 */
final class HttpUrlAdapter {

    @FromJson
    HttpUrl fromJson(final String url) {
        final String fixedUrl = url.startsWith("//") ? "http:" + url : url;

        return HttpUrl.parse(fixedUrl);
    }
}
