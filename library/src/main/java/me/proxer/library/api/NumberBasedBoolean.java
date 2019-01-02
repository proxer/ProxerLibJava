package me.proxer.library.api;

import com.squareup.moshi.JsonQualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation marking booleans to be number based in the API.
 * That means, that they are parsed from the values 1 (true) and 0 (false) and are also serialized to these.
 *
 * @author Ruben Gees
 */
@JsonQualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberBasedBoolean {
}
