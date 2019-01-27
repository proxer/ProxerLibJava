package me.proxer.library.api

import com.squareup.moshi.FromJson
import okhttp3.HttpUrl

/**
 * @author Ruben Gees
 */
internal class HttpUrlAdapter {

    @FromJson
    fun fromJson(url: String): HttpUrl? {
        val fixedUrl = if (url.startsWith("//")) "http:$url" else url

        return HttpUrl.parse(fixedUrl)
    }
}
