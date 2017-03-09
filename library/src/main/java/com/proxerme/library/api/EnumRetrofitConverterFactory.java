package com.proxerme.library.api;

import com.squareup.moshi.Json;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
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
            return new EnumConverter();
        }

        return null;
    }

    private static final class EnumConverter implements Converter<Enum<?>, String> {
        @Override
        public String convert(final Enum<?> e) throws IOException {
            try {
                return e.getClass().getField(e.name()).getAnnotation(Json.class).name();
            } catch (NoSuchFieldException ignored) {
                return null;
            }
        }
    }
}
