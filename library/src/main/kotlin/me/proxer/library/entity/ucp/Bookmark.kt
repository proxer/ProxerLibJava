package me.proxer.library.entity.ucp

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.api.NumberBasedBoolean
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.entity.info.Entry
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium

/**
 * Entity representing a bookmark of the user.
 *
 * @property entryId The id of the associated [Entry].
 * @property category The category.
 * @property name The name.
 * @property episode The episode.
 * @property language The language.
 * @property medium The medium.
 * @property state The state of the associated [Entry].
 * @property chapterName The name of the chapter if the associated media is a manga and it is uploaded.
 * @property isAvailable If this episode is available yet.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class Bookmark(
    @Json(name = "id") override val id: String,
    @Json(name = "eid") val entryId: String,
    @Json(name = "kat") val category: Category,
    @Json(name = "name") val name: String,
    @Json(name = "episode") val episode: Int,
    @Json(name = "language") val language: MediaLanguage,
    @Json(name = "medium") val medium: Medium,
    @Json(name = "state") val state: MediaState,
    @Json(name = "chapterName") val chapterName: String?,
    @field:NumberBasedBoolean @Json(name = "available") val isAvailable: Boolean
) : ProxerIdItem
