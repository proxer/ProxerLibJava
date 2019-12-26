package me.proxer.library.internal.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import com.squareup.moshi.internal.Util
import me.proxer.library.entity.info.RatingDetails
import okio.Buffer

/**
 * @author Ruben Gees
 */
internal class FixRatingDetailsAdapter {

    private companion object {
        private const val INVALID_ARRAY = "[]"
    }

    private val options: JsonReader.Options = JsonReader.Options.of(
        "genre", "story", "animation", "characters", "music"
    )

    @FromJson
    fun fromJson(jsonReader: JsonReader, intAdapter: JsonAdapter<Int>): RatingDetails? {
        val json = jsonReader.nextString()

        return when {
            json.isBlank() || json == INVALID_ARRAY -> RatingDetails(0, 0, 0, 0, 0)
            else -> readRatingDetails(json, intAdapter)
        }
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: RatingDetails?, intAdapter: JsonAdapter<Int>) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }

        writer.beginObject()
        writer.name("genre")
        intAdapter.toJson(writer, value.genre)
        writer.name("story")
        intAdapter.toJson(writer, value.story)
        writer.name("animation")
        intAdapter.toJson(writer, value.animation)
        writer.name("characters")
        intAdapter.toJson(writer, value.characters)
        writer.name("music")
        intAdapter.toJson(writer, value.music)
        writer.endObject()
    }

    private fun readRatingDetails(json: String, intAdapter: JsonAdapter<Int>): RatingDetails {
        val reader = JsonReader.of(Buffer().writeUtf8(json))

        var genre = 0
        var story = 0
        var animation = 0
        var characters = 0
        var music = 0

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> genre = intAdapter.fromJson(reader) ?: throw Util.unexpectedNull("genre", "genre", reader)
                1 -> story = intAdapter.fromJson(reader) ?: throw Util.unexpectedNull("story", "story", reader)
                2 -> animation = intAdapter.fromJson(reader) ?: throw Util.unexpectedNull(
                    "animation",
                    "animation", reader
                )
                3 -> characters = intAdapter.fromJson(reader) ?: throw Util.unexpectedNull(
                    "characters",
                    "characters", reader
                )
                4 -> music = intAdapter.fromJson(reader) ?: throw Util.unexpectedNull("music", "music", reader)
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()

        return RatingDetails(genre, story, animation, characters, music)
    }
}
