package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Filter for the {@link com.proxerme.library.connection.info.request.CommentRequest}.
 *
 * @author Desnoo
 */
public final class CommentSortParameter {

    public static final String RATING = "rating";
    public static final String NEWEST = "";

    private CommentSortParameter() {
    }

    /**
     * An annotation that represents the possible sort options.
     */
    @StringDef({RATING, NEWEST})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface CommentSort {
    }
}
