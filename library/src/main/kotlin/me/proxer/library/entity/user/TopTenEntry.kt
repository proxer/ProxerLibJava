package me.proxer.library.entity.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.Category
import me.proxer.library.enums.Medium

/**
 * Entity representing a single Topten entry.
 *
 * @property name The name.
 * @property category The category.
 * @property medium The medium.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class TopTenEntry(
    @Json(name = "eid") override val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "kat") val category: Category,
    @Json(name = "medium") val medium: Medium
) : ProxerIdItem
