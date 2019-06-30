package me.proxer.library.internal

import okhttp3.RequestBody
import okio.BufferedSink

/**
 * @author Ruben Gees
 */
internal class OneShotDelegatingRequestBody(private val delegate: RequestBody) : RequestBody() {

    override fun contentType() = delegate.contentType()
    override fun contentLength() = delegate.contentLength()
    override fun writeTo(sink: BufferedSink) = delegate.writeTo(sink)
    override fun isDuplex() = delegate.isDuplex()

    override fun isOneShot() = true
}
