package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the available tag rate filters.
 *
 * @author Ruben Gees
 */

public class TagRateFilterParameter {

    public static final String REGISTERED = "rate_1";
    public static final String UNDEFINED = "rate-10";

    /**
     * An annotation representing the available tag rate filters.
     */
    @StringDef({REGISTERED, UNDEFINED})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface TagRateFilter {
    }

}
