package me.proxer.library.internal.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.info.AdaptionInfo
import me.proxer.library.enums.Medium
import me.proxer.library.util.ProxerUtils

/**
 * @author Ruben Gees
 */
internal class AdaptionInfoAdapter {

    @FromJson
    fun fromJson(json: IntermediateAdaptionInfo): AdaptionInfo {
        return AdaptionInfo(json.id, json.name, ProxerUtils.toApiEnum<Medium>(json.medium))
    }

    @JsonClass(generateAdapter = true)
    internal data class IntermediateAdaptionInfo(
        @Json(name = "id") var id: String,
        @Json(name = "name") var name: String,
        @Json(name = "medium") var medium: String
    )
}
