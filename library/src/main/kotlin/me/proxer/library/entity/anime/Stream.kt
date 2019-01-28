package me.proxer.library.entity.anime

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerDateItem
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.entity.ProxerImageItem
import me.proxer.library.internal.adapter.NumberBasedBoolean
import java.util.Date

/**
 * Entity representing a single streaming option of an anime.
 *
 * @property hoster The type of hoster of this stream.
 * @property hosterName The name of the hoster.
 * @property uploaderId The id of the uploader.
 * @property uploaderName The username of the uploader.
 * @property translatorGroupId The id of the translator group, if present.
 * @property translatorGroupName The name of the translator group, if present.
 * @property isOfficial If the stream is hosted at an official hoster like Crunchyroll.
 * @property isPublic If the stream is public and should be shown to guests.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class Stream(
    @Json(name = "id") override val id: String,
    @Json(name = "type") val hoster: String,
    @Json(name = "name") val hosterName: String,
    @Json(name = "img") override val image: String,
    @Json(name = "uploader") val uploaderId: String,
    @Json(name = "username") val uploaderName: String,
    @Json(name = "timestamp") override val date: Date,
    @Json(name = "tid") val translatorGroupId: String?,
    @Json(name = "tname") val translatorGroupName: String?,
    @field:NumberBasedBoolean @Json(name = "legal") val isOfficial: Boolean,
    @field:NumberBasedBoolean @Json(name = "public") val isPublic: Boolean
) : ProxerIdItem, ProxerImageItem, ProxerDateItem
