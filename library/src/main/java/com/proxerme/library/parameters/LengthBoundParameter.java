package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the length bounds.
 *
 * @author Ruben Gees
 */
public class LengthBoundParameter {

    public static final String UP = "up";
    public static final String DOWN = "down";

    /**
     * An annotation representing the available length bounds.
     */
    @StringDef({UP, DOWN})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface LengthBound {
    }

}
