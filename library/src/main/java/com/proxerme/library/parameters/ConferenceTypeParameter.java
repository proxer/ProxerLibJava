package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class ConferenceTypeParameter {

    public static final String FAVORITE = "favour";
    public static final String BLOCKED = "block";
    public static final String GROUP = "group";
    public static final String DEFAULT = "default";

    /**
     * An annotation representing the available conference types.
     */
    @StringDef({FAVORITE, BLOCKED, GROUP, DEFAULT})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface ConferenceType {
    }

}
