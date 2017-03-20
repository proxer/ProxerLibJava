package me.proxer.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the available genres.
 *
 * @author Ruben Gees
 */
public enum Genre {
    @Json(name = "Abenteuer")ADVENTURE,
    @Json(name = "Action")ACTION,
    @Json(name = "Adult")ADULT,
    @Json(name = "Comedy")COMEDY,
    @Json(name = "Cyberpunk")CYBERPUNK,
    @Json(name = "Drama")DRAMA,
    @Json(name = "Ecchi")ECCHI,
    @Json(name = "Fantasy")FANTASY,
    @Json(name = "Harem")HAREM,
    @Json(name = "Historical")HISTORICAL,
    @Json(name = "Horror")HORROR,
    @Json(name = "Josei")JOSEI,
    @Json(name = "Magic")MAGIC,
    @Json(name = "Martial-Art")MARTIAL_ART,
    @Json(name = "Mecha")MECHA,
    @Json(name = "Military")MILITARY,
    @Json(name = "Musik")MUSIC,
    @Json(name = "Mystery")MYSTERY,
    @Json(name = "Psychological")PSYCHOLOGICAL,
    @Json(name = "Romance")ROMANCE,
    @Json(name = "School")SCHOOL,
    @Json(name = "SciFi")SCI_FI,
    @Json(name = "Seinen")SEINEN,
    @Json(name = "Shoujou")SHOUJOU,
    @Json(name = "Shoujou-Ai")SHOUJOU_AI,
    @Json(name = "Shounen")SHOUNEN,
    @Json(name = "Shounen-Ai")SHOUNEN_AI,
    @Json(name = "Slice_of_Life")SLICE_OF_LIFE,
    @Json(name = "Splatter")SPLATTER,
    @Json(name = "Sport")SPORT,
    @Json(name = "Superpower")SUPERPOWER,
    @Json(name = "Vampire")VAMPIRE,
    @Json(name = "Violence")VIOLENCE,
    @Json(name = "Yaoi")YAOI,
    @Json(name = "Yuri")YURI
}
