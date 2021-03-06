package me.proxer.library.internal.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.ToJson

/**
 * @author Ruben Gees
 */
internal class UnitAdapter {

    @FromJson
    fun fromJson(json: String): Unit? {
        return when (json) {
            "null" -> null
            else -> throw JsonDataException("Unit field can only host null values. Actual: $json")
        }
    }

    @Suppress("FunctionOnlyReturningConstant")
    @ToJson
    fun toJson(@Suppress("UNUSED_PARAMETER", "UnusedPrivateMember") nothingness: Unit?): String {
        return "null"
    }
}
