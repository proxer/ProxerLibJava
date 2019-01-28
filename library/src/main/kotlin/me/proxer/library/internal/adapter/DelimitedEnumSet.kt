package me.proxer.library.internal.adapter

import com.squareup.moshi.JsonQualifier

/**
 * @author Ruben Gees
 */
@JsonQualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class DelimitedEnumSet(val delimiter: String = " ")
