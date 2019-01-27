package me.proxer.library.entity.ucp

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.entity.info.Entry
import me.proxer.library.enums.Category
import me.proxer.library.enums.Medium

/**
 * Entity representing a single entry in the history list.
 *
 * @property entryId The id of the associated [Entry].
 * @property name The name.
 * @property medium The medium.
 * @property category The category.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class UcpTopTenEntry(
    @Json(name = "fid") override val id: String,
    @Json(name = "eid") val entryId: String,
    @Json(name = "name") val name: String,
    @Json(name = "medium") val medium: Medium,
    @Json(name = "kat") val category: Category
) : ProxerIdItem
