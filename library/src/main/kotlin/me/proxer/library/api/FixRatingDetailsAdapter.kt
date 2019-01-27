package me.proxer.library.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import me.proxer.library.entity.info.RatingDetails

/**
 * @author Ruben Gees
 */
internal class FixRatingDetailsAdapter {

    private companion object {
        private const val INVALID_ARRAY = "[]"
    }

    @FromJson
    fun fromJson(jsonReader: JsonReader, delegate: JsonAdapter<RatingDetails>): RatingDetails? {
        val json = jsonReader.nextString()

        return when {
            json.isBlank() || json == INVALID_ARRAY -> RatingDetails(0, 0, 0, 0, 0)
            else -> delegate.fromJson(json)
        }
    }
}
