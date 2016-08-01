package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing all available sort parameters for media list APIs.
 *
 * @author Ruben Gees
 */

public class MediaSortParameter {

    public static final String RELEVANCE = "relevance";
    public static final String CLICKS = "clicks";
    public static final String RATING = "rating";
    public static final String COUNT = "count";
    public static final String NAME = "name";

    /**
     * An annotation representing the available sort criteria for media lists.
     */
    @StringDef({RELEVANCE, CLICKS, RATING, COUNT, NAME})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface MediaSortCriteria {
    }

}
