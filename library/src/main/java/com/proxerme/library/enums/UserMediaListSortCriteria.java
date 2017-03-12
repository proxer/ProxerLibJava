package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the available sort types for the user media list.
 *
 * @author Ruben Gees
 */
public enum UserMediaListSortCriteria {
    @Json(name = "nameASC")NAME_ASCENDING,
    @Json(name = "nameDESC")NAME_DESCENDING,
    @Json(name = "stateNameASC")STATE_NAME_ASCENDING,
    @Json(name = "stateNameDESC")STATE_NAME_DESCENDING,
    @Json(name = "changeDateASC")CHANGE_DATE_ASCENDING,
    @Json(name = "changeDateDESC")CHANGE_DATE_DESCENDING,
    @Json(name = "stateChangeDateASC")STATE_CHANGE_DATE_ASCENDING,
    @Json(name = "stateChangeDateDESC")STATE_CHANGE_DATE_DESCENDING
}
