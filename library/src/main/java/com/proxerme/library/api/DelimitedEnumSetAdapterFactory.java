package com.proxerme.library.api;

import com.proxerme.library.enums.FskConstraint;
import com.proxerme.library.enums.Genre;
import com.squareup.moshi.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumSet;
import java.util.Set;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
class DelimitedEnumSetAdapterFactory implements JsonAdapter.Factory {

    @Override
    public JsonAdapter<?> create(final Type type, final Set<? extends Annotation> annotations, final Moshi moshi) {
        final Type rawType = Types.getRawType(type);

        if (rawType != Set.class || !(type instanceof ParameterizedType)) {
            return null;
        }

        final Type parameterType = ((ParameterizedType) type).getActualTypeArguments()[0];

        if (parameterType == Genre.class) {
            return new DelimitedEnumSetAdapter<>(Genre.class);
        } else if (parameterType == FskConstraint.class) {
            return new DelimitedEnumSetAdapter<>(FskConstraint.class);
        }

        return null;
    }

    private static class DelimitedEnumSetAdapter<T extends Enum<T>> extends JsonAdapter<Set<T>> {

        private static final String DELIMITER = " ";

        private final Class<T> enumType;

        DelimitedEnumSetAdapter(@NotNull final Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public Set<T> fromJson(final JsonReader reader) throws IOException {
            final String json = reader.nextString();

            if (json.isEmpty()) {
                return EnumSet.noneOf(enumType);
            }

            final String[] parts = json.split(DELIMITER);
            final EnumSet<T> result = EnumSet.noneOf(enumType);

            for (final String part : parts) {
                for (final Field field : enumType.getFields()) {
                    if (field.getAnnotation(Json.class).name().equals(part)) {
                        result.add(Enum.valueOf(enumType, field.getName()));
                    }
                }
            }

            return result;
        }

        @Override
        public void toJson(final JsonWriter writer, final Set<T> value) throws IOException {
            String result = "";

            for (final T item : value) {
                try {
                    result += enumType.getField(item.name()).getAnnotation(Json.class).name();
                } catch (final NoSuchFieldException ignored) {
                    throw new JsonDataException("Illegal item in set: " + item.name());
                }

                result += DELIMITER;
            }

            if (!result.isEmpty()) {
                result = result.substring(0, result.length() - DELIMITER.length());
            }

            writer.value(result);
        }
    }
}
