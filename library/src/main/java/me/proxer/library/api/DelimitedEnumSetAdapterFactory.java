package me.proxer.library.api;

import com.squareup.moshi.*;
import me.proxer.library.enums.FskConstraint;
import me.proxer.library.enums.Genre;
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.util.ProxerUtils;

import javax.annotation.Nullable;
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

        DelimitedEnumSetAdapter(final Class<T> enumType, final String delimiter) {
            this.enumType = enumType;
            this.delimiter = delimiter;
        }

        @Override
        public Set<T> fromJson(final JsonReader reader) throws IOException {
            final EnumSet<T> result = EnumSet.noneOf(enumType);
            final JsonReader.Token nextToken = reader.peek();
            final List<String> parts;

            if (nextToken == JsonReader.Token.BEGIN_ARRAY) {
                parts = new ArrayList<>();

                reader.beginArray();
                while (reader.hasNext()) {
                    parts.add(reader.nextString());
                }
                reader.endArray();
            } else if (nextToken == JsonReader.Token.NULL) {
                reader.nextNull();

                parts = Collections.emptyList();
            } else {
                parts = Arrays.asList(reader.nextString().split(delimiter));
            }

            for (final String part : parts) {
                for (final Field field : enumType.getFields()) {
                    Json annotation = field.getAnnotation(Json.class);

                    if (annotation != null && annotation.name().equalsIgnoreCase(part)) {
                        result.add(Enum.valueOf(enumType, field.getName()));

                        break;
                    }
                }
            }

            return result;
        }

        @Override
        public void toJson(final JsonWriter writer, @Nullable final Set<T> value) throws IOException {
            if (value == null) {
                return;
            }

            StringBuilder result = new StringBuilder();

            for (final T item : value) {
                result.append(ProxerUtils.getApiEnumName(item));
                result.append(delimiter);
            }

            if (result.length() > 0) {
                result = new StringBuilder(result.substring(0, result.length() - delimiter.length()));
            }

            writer.value(result.toString());
        }
    }
}
