package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the available tag spoiler filters.
 *
 * @author Ruben Gees
 */
public class TagSpoilerFilterParameter {

    public static final String NO_SPOILERS = "spoiler_0";
    public static final String ALL = "spoiler_10";
    public static final String SPOILERS = "spoiler_1";

    /**
     * An annotation representing the available tag rate filters.
     */
    @StringDef({NO_SPOILERS, ALL, SPOILERS})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface TagSpoilerFilter {
    }

}
