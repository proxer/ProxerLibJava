package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the available media types. This is almost the same as {@link MediumParameter},
 * but contains some abstract values.
 *
 * @author Ruben Gees
 */
public class TypeParameter {

    public static final String ANIMESERIES = "animeseries";
    public static final String MOVIE = "movie";
    public static final String OVA = "ova";
    public static final String HENTAI = "hentai";
    public static final String MANGASERIES = "mangaseries";
    public static final String ONESHOT = "oneshot";
    public static final String DOUJIN = "doujin";
    public static final String HMANGA = "hmanga";
    public static final String ALL_ANIME = "all-anime";
    public static final String ALL_MANGA = "all-manga";
    public static final String ALL = "all";
    public static final String ALL_WITH_HENTAI = "all18";

    /**
     * An annotation representing the available mediums.
     */
    @StringDef({ANIMESERIES, MOVIE, OVA, HENTAI, MANGASERIES, ONESHOT, DOUJIN, HMANGA, ALL_ANIME,
            ALL_MANGA, ALL, ALL_WITH_HENTAI})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface Type {
    }
}
