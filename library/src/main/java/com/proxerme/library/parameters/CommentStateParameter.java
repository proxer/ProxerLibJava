package com.proxerme.library.parameters;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the available states of a comment associated with a media entry.
 *
 * @author Ruben Gees
 */
public class CommentStateParameter {

    public static final int WATCHED = 0;
    public static final int WATCHING = 1;
    public static final int WILL_WATCH = 2;
    public static final int CANCELLED = 3;

    /**
     * An annotation that represents the possible season.
     */
    @IntDef({WATCHED, WATCHING, WILL_WATCH, CANCELLED})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface CommentState {
    }

}
