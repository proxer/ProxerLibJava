package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the possible view states of animes/mangas of an user.
 *
 * @author Desnoo
 */
public final class ViewStateParameter {

    public static final String WATCHLIST = "note";
    public static final String FAVOURITE = "favor";
    public static final String FINISHED = "finish";

    private ViewStateParameter() {
    }

    /**
     * An annotation that represents the possible view states.
     */
    @StringDef({WATCHLIST, FAVOURITE, FINISHED})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface ViewState {
    }
}
