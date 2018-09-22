package me.proxer.library.enums;

import com.serjltt.moshi.adapters.FallbackEnum;
import com.squareup.moshi.Json;

/**
 * Enum holding the available genres.
 *
 * @author Ruben Gees
 */
@FallbackEnum(name = "UNKNOWN")
public enum Genre {
    @Json(name = "Action") ACTION,
    @Json(name = "Adult") ADULT,
    @Json(name = "Adventure") ADVENTURE,
    @Json(name = "Comedy") COMEDY,
    @Json(name = "Cyberpunk") CYBERPUNK,
    @Json(name = "Drama") DRAMA,
    @Json(name = "Ecchi") ECCHI,
    @Json(name = "Fantasy") FANTASY,
    @Json(name = "Harem") HAREM,
    @Json(name = "Historical") HISTORICAL,
    @Json(name = "Horror") HORROR,
    @Json(name = "Josei") JOSEI,
    @Json(name = "Kids") KIDS,
    @Json(name = "Krimi") KRIMI,
    @Json(name = "Magic") MAGIC,
    @Json(name = "Martial-Art") MARTIAL_ART,
    @Json(name = "Mecha") MECHA,
    @Json(name = "Military") MILITARY,
    @Json(name = "Musik") MUSIC,
    @Json(name = "Mystery") MYSTERY,
    @Json(name = "Psychological") PSYCHOLOGICAL,
    @Json(name = "Romance") ROMANCE,
    @Json(name = "School") SCHOOL,
    @Json(name = "SciFi") SCI_FI,
    @Json(name = "Seinen") SEINEN,
    @Json(name = "Shoujou") SHOUJOU,
    @Json(name = "Shoujou-Ai") SHOUJOU_AI,
    @Json(name = "Shounen") SHOUNEN,
    @Json(name = "Shounen-Ai") SHOUNEN_AI,
    @Json(name = "Slice of Life") SLICE_OF_LIFE,
    @Json(name = "Smut") SMUT,
    @Json(name = "Splatter") SPLATTER,
    @Json(name = "Sport") SPORT,
    @Json(name = "Steampunk") STEAMPUNK,
    @Json(name = "Supernatural") SUPERNATURAL,
    @Json(name = "Superpower") SUPERPOWER,
    @Json(name = "Violence") VIOLENCE,
    @Json(name = "Yaoi") YAOI,
    @Json(name = "Yuri") YURI,
    @Json(name = "") UNKNOWN
}
