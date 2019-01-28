package me.proxer.library.internal.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import me.proxer.library.enums.UcpSettingConstraint
import me.proxer.library.util.ProxerUtils

/**
 * @author Ruben Gees
 */
internal class UcpSettingConstraintAdapter {

    private val nameToValue: Map<Int, UcpSettingConstraint>
    private val valueToName: Map<UcpSettingConstraint, Int>

    init {
        val nameValuePairs = UcpSettingConstraint::class.java.enumConstants
            .map { ProxerUtils.getSafeApiEnumName(it).toInt() to it }

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
}
