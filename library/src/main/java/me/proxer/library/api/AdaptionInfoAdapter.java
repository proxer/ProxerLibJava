package me.proxer.library.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;
import me.proxer.library.entity.info.AdaptionInfo;
import me.proxer.library.enums.Medium;
import me.proxer.library.util.ProxerUtils;

/**
 * @author Ruben Gees
 */
class AdaptionInfoAdapter {

    @FromJson
    AdaptionInfo fromJson(final IntermediateAdaptionInfo json) {
        return new AdaptionInfo(json.id, json.name, ProxerUtils.toApiEnum(Medium.class, json.medium));
    }

    static class IntermediateAdaptionInfo {

        @Json(name = "id")
        String id;

        @Json(name = "name")
        String name;

        @Json(name = "medium")
        String medium;
    }
}
