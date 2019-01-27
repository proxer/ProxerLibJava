package me.proxer.library.internal.adapter

import me.proxer.library.util.ProxerUtils
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * @author Ruben Gees
 */
internal class EnumRetrofitConverterFactory : Converter.Factory() {

    override fun stringConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<Enum<*>, String>? {
        return when {
            (type as Class<*>).isEnum -> Converter { ProxerUtils.getSafeApiEnumName(it) }
            else -> null
        }
    }
}
