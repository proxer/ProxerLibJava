package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing all available mediums for some APIs.
 *
 * @author Ruben Gees
 */
public class MediumParameter {

    public static final String ANIMESERIES = "animeseries";
    public static final String MOVIE = "movie";
    public static final String OVA = "ova";
    public static final String HENTAI = "hentai";
    public static final String MANGASERIES = "mangaseries";
    public static final String ONESHOT = "oneshot";
    public static final String DOUJIN = "doujin";
    public static final String HMANGA = "hmanga";

    /**
     * An annotation representing the available mediums.
     */
    @StringDef({ANIMESERIES, MOVIE, OVA, HENTAI, MANGASERIES, ONESHOT, DOUJIN, HMANGA})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface Medium {
    }

}
