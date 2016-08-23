package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the available languages, used in searching.
 *
 * @author Ruben Gees
 */
public class LanguageParameter {

    public static final String GERMAN = "de";
    public static final String ENGLISH = "en";

    /**
     * An annotation representing the available languages.
     */
    @StringDef({GERMAN, ENGLISH})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface Language {
    }

}
