package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the types of a [me.proxer.library.entity.list.Tag].
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
@FallbackEnum(name = "OTHER")
enum class TagType {

    @Json(name = "entry_genre")
    GENRE,

    @Json(name = "entry_tag")
    TAG,

    @Json(name = "entry_tag_h")
    H_TAG,

    @Json(name = "character")
    CHARACTER,

    @Json(name = "")
    OTHER
}
