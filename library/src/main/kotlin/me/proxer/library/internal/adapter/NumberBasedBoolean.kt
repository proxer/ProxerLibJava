package me.proxer.library.internal.adapter

import com.squareup.moshi.JsonQualifier

/**
 * Annotation marking booleans to be number based in the API.
 * That means, that they are parsed from the values 1 (true) and 0 (false) and are also serialized to these.
 *
 * @author Ruben Gees
 */
@JsonQualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NumberBasedBoolean
