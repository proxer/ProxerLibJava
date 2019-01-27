package me.proxer.library.api

import com.squareup.moshi.JsonQualifier

/**
 * @author Ruben Gees
 */
@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class DelimitedEnumSet(val delimiter: String = " ")
