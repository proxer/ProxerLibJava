package com.proxerme.library.parameters;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class of the SeasonParameter. This includes the possible yearly seasons.
 *
 * @author Desnoo
 */
public final class SeasonParameter {

    public static final int UNSPECIFIED_ALT = 0;
    public static final int WINTER = 1;
    public static final int SPRING = 2;
    public static final int SUMMER = 3;
    public static final int AUTUMN = 4;
    public static final int UNSPECIFIED = 5;

    private SeasonParameter() {
    }

    /**
     * An annotation that represents the possible seasons.
     */
    @IntDef({UNSPECIFIED_ALT, SPRING, SUMMER, AUTUMN, WINTER, UNSPECIFIED})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface SeasonConstraint {
    }
}
