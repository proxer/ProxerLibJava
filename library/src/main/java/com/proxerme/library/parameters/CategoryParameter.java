package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the available categories for some APIs.
 *
 * @author Ruben Gees
 */

public class CategoryParameter {

    public static final String ANIME = "anime";
    public static final String MANGA = "manga";

    /**
     * An annotation representing the available categories.
     */
    @StringDef({ANIME, MANGA})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface Category {
    }

}
