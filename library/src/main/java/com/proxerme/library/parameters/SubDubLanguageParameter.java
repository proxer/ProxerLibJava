package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the sub/dub languages of Anime.
 *
 * @author Ruben Gees
 */
public class SubDubLanguageParameter {

    public static final String GERMAN_SUB = "gersub";
    public static final String GERMAN_DUB = "gerdub";
    public static final String ENGLISH_SUB = "engsub";
    public static final String ENGLISH_DUB = "engdub";

    /**
     * An annotation representing the available sub/dub languages.
     */
    @StringDef({GERMAN_SUB, GERMAN_DUB, ENGLISH_SUB, ENGLISH_DUB})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface SubDubLanguage {
    }

}
