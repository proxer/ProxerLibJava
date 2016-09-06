package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing all languages, general and sub/dub ones.
 *
 * @author Ruben Gees
 */
public final class LanguageParameter {

    private LanguageParameter() {
    }

    /**
     * An annotation representing the available languages.
     */
    @StringDef({GeneralLanguageParameter.GERMAN, GeneralLanguageParameter.ENGLISH,
            SubDubLanguageParameter.ENGLISH_DUB, SubDubLanguageParameter.ENGLISH_SUB,
            SubDubLanguageParameter.GERMAN_DUB, SubDubLanguageParameter.GERMAN_SUB})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface Language {
    }

}
