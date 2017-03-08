package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * TODO: Create class
 *
 * @author Ruben Gees
 */
public enum FskConstraint {
    @Json(name = "fsk0")FSK_O,
    @Json(name = "fsk6")FSK_6,
    @Json(name = "fsk12")FSK_12,
    @Json(name = "fsk16")FSK_16,
    @Json(name = "fsk18")FSK_18,
    @Json(name = "bad_language")BAD_LANGUAGE,
    @Json(name = "violence")VIOLENCE,
    @Json(name = "fear")FEAR,
    @Json(name = "sex")SEX
}
