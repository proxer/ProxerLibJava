package com.proxerme.library.parameters;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class of the SasonParameter. This includes the possible yearly seasons.
 *
 * @author Desnoo
 */
public class SeasonParameter {

    public static final int WINTER = 1;
    public static final int SPRING = 2;
    public static final int SUMMER = 3;
    public static final int AUTUMN = 4;

    /**
     * An annotation that represents the possible season.
     */
    @IntDef({SPRING, SUMMER, AUTUMN, WINTER})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER,})
    public @interface SeasonConstraint {
    }
}
