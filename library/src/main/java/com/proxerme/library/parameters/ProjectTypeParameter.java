package com.proxerme.library.parameters;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the available states of a project.
 *
 * @author Ruben Gees
 */
public class ProjectTypeParameter {

    public static final int UNDEFINED = 0;
    public static final int FINISHED = 1;
    public static final int IN_WORK = 2;
    public static final int PLANNED = 3;
    public static final int CANCELLED = 4;
    public static final int LICENCED = 5;

    private ProjectTypeParameter() {
    }

    /**
     * An annotation that represents the possible project types.
     */
    @IntDef({UNDEFINED, FINISHED, IN_WORK, PLANNED, CANCELLED, LICENCED})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface ProjectType {
    }
}
