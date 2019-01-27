package me.proxer.library.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * @author Ruben Gees
 */
internal class DateAdapter {

    companion object {
        // The API returns seconds so multiply by 1000.
        private const val DATE_MULTIPLICAND = 1000

        private val dateFormatWithTime = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.GERMANY)
        private val dateFormatWithoutTime = SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY)
    }

    @FromJson
    fun fromJson(date: String): Date {
        return date.toLongOrNull().let { dateAsLong ->
            when {
                dateAsLong != null -> Date(dateAsLong * DATE_MULTIPLICAND)
                date.contains(":") -> dateFormatWithTime.parse(date)
                else -> dateFormatWithoutTime.parse(date)
            }
        }
    }

    @ToJson
    fun toJson(date: Date?): Long {
        return date?.time ?: 0
    }
}
