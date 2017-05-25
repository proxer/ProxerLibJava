package me.proxer.library.api;

import me.proxer.library.util.ProxerUtils;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * @author Ruben Gees
 */
final class EnumRetrofitConverterFactory extends Converter.Factory {

    @Override
    public Converter<?, String> stringConverter(final Type type, final Annotation[] annotations,
                                                final Retrofit retrofit) {
        if (((Class<?>) type).isEnum()) {
            return (Converter<Enum<?>, String>) ProxerUtils::getApiEnumName;
        }

        return null;
    }
}
