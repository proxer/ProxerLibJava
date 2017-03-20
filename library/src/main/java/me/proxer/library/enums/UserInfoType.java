package me.proxer.library.enums;

import com.squareup.moshi.Json;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public enum UserInfoType {
    @Json(name = "note")NOTE,
    @Json(name = "favor")FAVORITE,
    @Json(name = "finish")FINISHED,
}
