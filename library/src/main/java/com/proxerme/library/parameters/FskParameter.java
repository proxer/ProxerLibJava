package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the available Fsk constraints.
 *
 * @author Ruben Gees
 */

public class FskParameter {

    public static final String FSK_0 = "fsk0";
    public static final String FSK_6 = "fsk6";
    public static final String FSK_12 = "fsk12";
    public static final String FSK_16 = "fsk16";
    public static final String FSK_18 = "fsk18";
    public static final String BAD_LANGUAGE = "bad_language";
    public static final String VIOLENCE = "violence";
    public static final String FEAR = "fear";
    public static final String SEX = "sex";

    /**
     * An annotation representing the available fsk constraints.
     */
    @StringDef({FSK_0, FSK_6, FSK_12, FSK_16, FSK_18, BAD_LANGUAGE, VIOLENCE, FEAR, SEX})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface FskConstraint {
    }

}
