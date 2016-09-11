package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the available genres. Those are used for searching.
 *
 * @author Ruben Gees
 */
public final class GenreParameter {

    public static final String ADVENTURE = "Abenteuer";
    public static final String ACTION = "Action";
    public static final String ADULT = "Adult";
    public static final String COMEDY = "Comedy";
    public static final String CYBERPUNK = "Cyberpunk";
    public static final String DRAMA = "Drama";
    public static final String ECCHI = "Ecchi";
    public static final String FANTASY = "Fantasy";
    public static final String HAREM = "Harem";
    public static final String HISTORICAL = "Historical";
    public static final String HORROR = "Horror";
    public static final String JOSEI = "Josei";
    public static final String MAGIC = "Magic";
    public static final String MARTIAL_ART = "Martial-Art";
    public static final String MECHA = "Mecha";
    public static final String MILITARY = "Military";
    public static final String MUSIC = "Musik";
    public static final String MYSTERY = "Mystery";
    public static final String PSYCHOLOGICAL = "Psychological";
    public static final String ROMANCE = "Romance";
    public static final String SCHOOL = "School";
    public static final String SCI_FI = "SciFi";
    public static final String SEINEN = "Seinen";
    public static final String SHOUJOU = "Shoujou";
    public static final String SHOUJOU_AI = "Shoujou-Ai";
    public static final String SHOUNEN = "Shounen";
    public static final String SHOUNEN_AI = "Shounen-Ai";
    public static final String SLICE_OF_LIFE = "Slice_of_Life";
    public static final String SPLATTER = "Splatter";
    public static final String SPORT = "Sport";
    public static final String SUPERPOWER = "Superpower";
    public static final String VAMPIRE = "Vampire";
    public static final String VIOLENCE = "Violence";
    public static final String YAOI = "Yaoi";
    public static final String YURI = "Yuri";
    /**
     * An array of all available genres for use in a list.
     */
    public static String[] genres = new String[]{ADVENTURE, ACTION, ADULT, COMEDY, CYBERPUNK, DRAMA,
            ECCHI, FANTASY, HAREM, HISTORICAL, HORROR, JOSEI, MAGIC, MARTIAL_ART, MECHA, MILITARY,
            MUSIC, MYSTERY, PSYCHOLOGICAL, ROMANCE, SCHOOL, SCI_FI, SEINEN, SHOUJOU, SHOUJOU_AI,
            SHOUNEN, SHOUNEN_AI, SLICE_OF_LIFE, SPLATTER, SPORT, SUPERPOWER, VAMPIRE, VIOLENCE,
            YAOI, YURI,};

    private GenreParameter() {
    }

    /**
     * An annotation representing the available languages.
     */
    @StringDef({ADVENTURE, ACTION, ADULT, COMEDY, CYBERPUNK, DRAMA, ECCHI, FANTASY, HAREM,
            HISTORICAL, HORROR, JOSEI, MAGIC, MARTIAL_ART, MECHA, MILITARY, MUSIC, MYSTERY,
            PSYCHOLOGICAL, ROMANCE, SCHOOL, SCI_FI, SEINEN, SHOUJOU, SHOUJOU_AI, SHOUNEN,
            SHOUNEN_AI, SLICE_OF_LIFE, SPLATTER, SPORT, SUPERPOWER, VAMPIRE, VIOLENCE, YAOI, YURI})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface Genre {
    }

}
