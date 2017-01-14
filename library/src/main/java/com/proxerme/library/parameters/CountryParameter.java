package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the the available countries, used for translator groups and industries.
 *
 * @author Ruben Gees
 */
public class CountryParameter {

    public static final String GERMAN = "de";
    public static final String ENGLISH = "en";
    public static final String UNITED_STATES = "us";
    public static final String JAPANESE = "jp";
    public static final String MISCELLANEOUS = "misc";

    private CountryParameter() {
    }

    /**
     * An annotation representing the available countries.
     */
    @StringDef({GERMAN, ENGLISH, UNITED_STATES, JAPANESE, MISCELLANEOUS})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface Country {
    }
}
