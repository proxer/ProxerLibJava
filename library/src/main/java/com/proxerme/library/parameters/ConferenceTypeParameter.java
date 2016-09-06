package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the available types of a
 * {@link com.proxerme.library.connection.messenger.entity.Conference}.
 *
 * @author Ruben Gees
 */
public final class ConferenceTypeParameter {

    public static final String FAVORITE = "favour";
    public static final String BLOCKED = "block";
    public static final String GROUP = "group";
    public static final String DEFAULT = "default";

    private ConferenceTypeParameter() {
    }

    /**
     * An annotation representing the available conference types.
     */
    @StringDef({FAVORITE, BLOCKED, GROUP, DEFAULT})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface ConferenceType {
    }

}
