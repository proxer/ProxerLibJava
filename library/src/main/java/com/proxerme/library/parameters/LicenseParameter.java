package com.proxerme.library.parameters;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the licensing parameter.
 *
 * @author Desnoo
 */
public class LicenseParameter {

    public static final int UNKNOWN = 0;
    public static final int NON_LICENSED = 1;
    public static final int LICENSED = 2;

    /**
     * An annotation representing the license parameters.
     */
    @IntDef({UNKNOWN, NON_LICENSED, LICENSED})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface License {
    }

}
