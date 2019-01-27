@file:Suppress("NOTHING_TO_INLINE")

package me.proxer.library.internal.util

internal inline fun Boolean?.toIntOrNull() = when (this) {
    true -> 1
    false -> 0
    null -> null
}
