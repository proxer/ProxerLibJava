package com.proxerme.library.parameters;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class holding values for controlling if hentai should be shown in APIs which could contain them.
 *
 * @author Ruben Gees
 */
public final class ShowHentaiParameter {

    public static final int NO_HENTAI = -1;
    public static final int BOTH = 0;
    public static final int ONLY_HENTAI = 1;

    private ShowHentaiParameter() {
    }

    /**
     * An annotation that represents the possible options for showing hentai.
     */
    @IntDef({NO_HENTAI, BOTH, ONLY_HENTAI})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface ShowHentai {
    }
}
