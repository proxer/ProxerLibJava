package me.proxer.library.internal.adapter

import com.squareup.moshi.FromJson
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

/**
 * @author Ruben Gees
 */
internal class HttpUrlAdapter {

    @FromJson
    fun fromJson(url: String): HttpUrl? {
        val fixedUrl = if (url.startsWith("//")) "http:$url" else url

        return fixedUrl.toHttpUrlOrNull()
    }
}
