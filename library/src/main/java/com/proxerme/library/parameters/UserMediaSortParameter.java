package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing all available sort criteria for some APIs
 *
 * @author Ruben Gees
 */
public final class UserMediaSortParameter {

    public static final String NAME_ASCENDING = "nameASC";
    public static final String NAME_DESCENDING = "nameDESC";
    public static final String STATE_NAME_ASCENDING = "stateNameASC";
    public static final String STATE_NAME_DESCENDING = "stateNameDESC";
    public static final String CHANGE_DATE_ASCENDING = "changeDateASC";
    public static final String CHANGE_DATE_DESCENDING = "changeDateDESC";
    public static final String STATE_CHANGE_DATE_ASCENDING = "stateChangeDateASC";
    public static final String STATE_CHANGE_DATE_DESCENDING = "stateChangeDateDESC";

    private UserMediaSortParameter() {
    }

    /**
     * An annotation representing the available sort criteria for user media lists.
     */
    @StringDef({NAME_ASCENDING, NAME_DESCENDING, STATE_NAME_ASCENDING, STATE_NAME_DESCENDING,
            CHANGE_DATE_ASCENDING, CHANGE_DATE_DESCENDING, STATE_CHANGE_DATE_ASCENDING,
            STATE_CHANGE_DATE_DESCENDING})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface UserMediaSortCriteria {
    }
}
