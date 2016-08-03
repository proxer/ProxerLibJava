package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

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

    public static final String SPRING = "TODO";
    public static final String SUMMER = "TODO";
    public static final String AUTUMN = "TODO";
    public static final String WINTER = "TODO";

    /**
     * An annotation that represents the possible season.
     */
    @StringDef({SPRING, SUMMER, AUTUMN, WINTER})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER,})
    public @interface SeasonConstraint {
    }
}
