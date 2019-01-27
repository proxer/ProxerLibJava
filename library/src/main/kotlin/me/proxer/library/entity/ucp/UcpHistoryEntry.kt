package me.proxer.library.entity.ucp

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerDateItem
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.enums.Medium
import java.util.Date

/**
 * Entity representing a single entry in the history list.
 *
 * @property entryId The id of the associated [me.proxer.library.entity.info.Entry].
 * @property name The name.
 * @property language The language.
 * @property medium The medium.
 * @property category The category.
 * @property episode The episode.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class UcpHistoryEntry(
    @Json(name = "id") override val id: String,
    @Json(name = "eid") val entryId: String,
    @Json(name = "name") val name: String,
    @Json(name = "language") val language: MediaLanguage,
    @Json(name = "medium") val medium: Medium,
    @Json(name = "kat") val category: Category,
    @Json(name = "episode") val episode: Int,
    @Json(name = "timestamp") override val date: Date
) : ProxerIdItem, ProxerDateItem
