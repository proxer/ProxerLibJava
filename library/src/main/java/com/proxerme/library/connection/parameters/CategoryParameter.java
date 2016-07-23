package com.proxerme.library.connection.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public class CategoryParameter {

    public static final String ANIME = "anime";
    public static final String MANGA = "manga";

    @StringDef({ANIME, MANGA})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface Category {
    }

}
