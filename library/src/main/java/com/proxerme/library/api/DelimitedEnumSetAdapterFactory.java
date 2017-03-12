package com.proxerme.library.api;

import com.proxerme.library.enums.FskConstraint;
import com.proxerme.library.enums.Genre;
import com.proxerme.library.enums.MediaLanguage;
import com.proxerme.library.util.Utils;
import com.squareup.moshi.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author Ruben Gees
 */
class DelimitedEnumSetAdapterFactory implements JsonAdapter.Factory {

    private static final String DELIMITER = " ";
    private static final String COMMA_DELIMITER = ",";

    @Override
    public JsonAdapter<?> create(final Type type, final Set<? extends Annotation> annotations, final Moshi moshi) {
        final Type rawType = Types.getRawType(type);

        if (rawType != Set.class || !(type instanceof ParameterizedType)) {
            return null;
        }

        final Type parameterType = ((ParameterizedType) type).getActualTypeArguments()[0];

        if (parameterType == Genre.class) {
            return new DelimitedEnumSetAdapter<>(Genre.class, DELIMITER);
        } else if (parameterType == FskConstraint.class) {
            return new DelimitedEnumSetAdapter<>(FskConstraint.class, DELIMITER);
        } else if (parameterType == MediaLanguage.class) {
            return new DelimitedEnumSetAdapter<>(MediaLanguage.class, COMMA_DELIMITER);
        }

        return null;
    }

    private static class DelimitedEnumSetAdapter<T extends Enum<T>> extends JsonAdapter<Set<T>> {

        private final Class<T> enumType;
        private final String delimiter;

        DelimitedEnumSetAdapter(@NotNull final Class<T> enumType, @NotNull final String delimiter) {
            this.enumType = enumType;
            this.delimiter = delimiter;
        }

        @Override
        public Set<T> fromJson(final JsonReader reader) throws IOException {
            final EnumSet<T> result = EnumSet.noneOf(enumType);
            final List<String> parts;

            if (reader.peek() == JsonReader.Token.BEGIN_ARRAY) {
                parts = new ArrayList<>();

                reader.beginArray();
                while (reader.hasNext()) {
                    parts.add(reader.nextString());
                }
                reader.endArray();
            } else {
                final String json = reader.nextString();

                if (json.isEmpty()) {
                    return EnumSet.noneOf(enumType);
                }

                parts = Arrays.asList(json.split(delimiter));
            }

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
                    result += Utils.getEnumName(item);
                } catch (final NoSuchFieldException ignored) {
                    throw new JsonDataException("Illegal item in set: " + item.name());
                }

                result += delimiter;
            }

            if (!result.isEmpty()) {
                result = result.substring(0, result.length() - delimiter.length());
            }

            writer.value(result);
        }
    }
}
