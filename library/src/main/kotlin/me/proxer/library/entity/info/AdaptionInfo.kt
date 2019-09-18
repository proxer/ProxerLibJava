package me.proxer.library.entity.info

import com.squareup.moshi.Json
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.Medium

/**
 * Entity holding info related to an adaption of an [Entry].
 *
 * @property name The name.
 * @property medium The medium.
 */
data class AdaptionInfo(
    @Json(name = "id") override val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "medium") val medium: Medium?
) : ProxerIdItem
