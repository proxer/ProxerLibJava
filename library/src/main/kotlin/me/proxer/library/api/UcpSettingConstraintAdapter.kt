package me.proxer.library.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.ToJson
import me.proxer.library.enums.UcpSettingConstraint
import java.lang.reflect.Field

/**
 * @author Ruben Gees
 */
internal class UcpSettingConstraintAdapter {

    private val nameToValue: Map<Int, UcpSettingConstraint>
    private val valueToName: Map<UcpSettingConstraint, Int>

    init {
        val nameValuePairs = UcpSettingConstraint::class.java.fields.map {
            it.constraintName to it.constraintValue
        }

        nameToValue = nameValuePairs.toMap()
        valueToName = nameValuePairs.map { (name, value) -> value to name }.toMap()
    }

    @ToJson
    fun toJson(constraint: UcpSettingConstraint): Int {
        return valueToName.getValue(constraint)
    }

    @FromJson
    fun fromJson(value: Int): UcpSettingConstraint {
        return nameToValue.getValue(value)
    }

    private inline val Field.constraintName
        get() = Integer.parseInt(this.getAnnotation(Json::class.java).name)

    private inline val Field.constraintValue
        get() = enumValueOf<UcpSettingConstraint>(name)
}
