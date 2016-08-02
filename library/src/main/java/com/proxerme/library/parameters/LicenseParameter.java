package com.proxerme.library.parameters;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Enum Containing the licensing parameter.
 * Created by Desnoo on 01.08.16.
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
