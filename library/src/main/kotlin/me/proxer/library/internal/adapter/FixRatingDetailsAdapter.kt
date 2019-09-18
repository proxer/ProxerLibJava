package me.proxer.library.internal.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
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

    @ToJson
    fun toJson(writer: JsonWriter, value: RatingDetails?, delegate: JsonAdapter<RatingDetails>) {
        delegate.toJson(writer, value)
    }
}
