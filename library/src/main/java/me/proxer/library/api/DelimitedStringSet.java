package me.proxer.library.api;

import com.squareup.moshi.JsonQualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Ruben Gees
 */
@Retention(RetentionPolicy.RUNTIME)
@JsonQualifier
public @interface DelimitedStringSet {

    String delimiter() default " ";

    String[] valuesToKeep() default {};
}
