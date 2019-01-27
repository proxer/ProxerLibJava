package me.proxer.library.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import me.proxer.library.entity.manga.Page

/**
 * @author Ruben Gees
 */
internal class PageAdapter {

    private companion object {
        private const val FIELD_AMOUNT = 3

        private const val NAME_FIELD_LOCATION = 0
        private const val HEIGHT_FIELD_LOCATION = 1
        private const val WIDTH_FIELD_LOCATION = 2
    }

    @FromJson
    fun fromJson(json: Array<Array<String>>): List<Page> {
        return json.map { jsonPage ->
            if (jsonPage.size != FIELD_AMOUNT) {
                throw JsonDataException("Page array length is " + json.size + " instead of 3.")
            }

            Page(
                jsonPage[NAME_FIELD_LOCATION],
                jsonPage[HEIGHT_FIELD_LOCATION].toInt(),
                jsonPage[WIDTH_FIELD_LOCATION].toInt()
            )
        }
    }
}
