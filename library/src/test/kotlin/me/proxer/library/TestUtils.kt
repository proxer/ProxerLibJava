package me.proxer.library

import okio.Okio
import java.lang.invoke.MethodHandles

fun fromResource(file: String): String {
    return MethodHandles.lookup().lookupClass().classLoader.getResourceAsStream(file).let {
        Okio.buffer(Okio.source(it)).readUtf8()
    }
}
