package com.proxerme.library.api;

import com.squareup.moshi.Json;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

final class EnumRetrofitConverterFactory extends Converter.Factory {

    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type instanceof Class && ((Class<?>) type).isEnum()) {
            return new Converter<Enum<?>, String>() {
                @Override
                public String convert(final Enum<?> e) throws IOException {
                    try {
                        return e.getClass().getField(e.name()).getAnnotation(Json.class).name();
                    } catch (NoSuchFieldException ignored) {
                        return null;
                    }
                }
            };
        }

        return null;
    }
}
