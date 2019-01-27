package me.proxer.library.entity.manga

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerDateItem
import me.proxer.library.entity.ProxerIdItem
import java.util.Date

/**
 * Entity representing a chapter in a single language of a manga.
 *
 * @property entryId The id of the associated entry.
 * @property title The title.
 * @property uploaderId The id of the uploader.
 * @property uploaderName The username of the uploader.
 * @property scanGroupId The id of the scan group if present.
 * @property scanGroupName The name of the scan group if present.
 * @property server The server this page is saved on. To be used for retrieving the url.
 * @property pages The actual pages of the chapter.
 * This being null means either that the server has problems or the media has been removed.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class Chapter(
    @Json(name = "cid") override val id: String,
    @Json(name = "eid") val entryId: String,
    @Json(name = "title") val title: String,
    @Json(name = "uploader") val uploaderId: String,
    @Json(name = "username") val uploaderName: String,
    @Json(name = "timestamp") override val date: Date,
    @Json(name = "tid") val scanGroupId: String?,
    @Json(name = "tname") val scanGroupName: String?,
    @Json(name = "server") val server: String,
    @Json(name = "pages") val pages: List<Page>?
) : ProxerIdItem, ProxerDateItem
