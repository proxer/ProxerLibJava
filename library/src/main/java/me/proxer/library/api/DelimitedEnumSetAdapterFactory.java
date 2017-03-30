package me.proxer.library.api;

import com.squareup.moshi.*;
import me.proxer.library.enums.FskConstraint;
import me.proxer.library.enums.Genre;
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.util.ProxerUtils;
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
                parts = Arrays.asList(reader.nextString().split(delimiter));
            }

            for (final String part : parts) {
                for (final Field field : enumType.getFields()) {
                    if (field.getAnnotation(Json.class).name().equals(part)) {
                        result.add(Enum.valueOf(enumType, field.getName()));

                        break;
                    }
                }
            }

            return result;
        }

        @Override
        public void toJson(final JsonWriter writer, final Set<T> value) throws IOException {
            StringBuilder result = new StringBuilder();

            for (final T item : value) {
                try {
                    result.append(ProxerUtils.getApiEnumName(item));
                } catch (final NoSuchFieldException ignored) {
                    throw new JsonDataException("Illegal item in set: " + item.name());
                }

                result.append(delimiter);
            }

            if (result.length() > 0) {
                result = new StringBuilder(result.substring(0, result.length() - delimiter.length()));
            }

            writer.value(result.toString());
        }
    }
}
