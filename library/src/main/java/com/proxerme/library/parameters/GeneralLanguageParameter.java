package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the general available languages, used in searching.
 *
 * @author Ruben Gees
 */
public final class GeneralLanguageParameter {

    public static final String GERMAN = "de";
    public static final String ENGLISH = "en";

    private GeneralLanguageParameter() {
    }

    /**
     * An annotation representing the available general languages.
     */
    @StringDef({GERMAN, ENGLISH})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface GeneralLanguage {
    }

}
