package me.proxer.library.api;

import com.serjltt.moshi.adapters.FallbackEnum;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import me.proxer.library.enums.FskConstraint;
import me.proxer.library.enums.Genre;
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.util.ProxerUtils;

import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ruben Gees
 */
class DelimitedEnumSetAdapterFactory implements JsonAdapter.Factory {

    private static final String DELIMITER = " ";
    private static final String COMMA_DELIMITER = ",";

    @Nullable
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
            final List<String> parts = parseParts(reader);

            return convertToEnumConstants(reader, parts);
        }

        @Override
        public void toJson(final JsonWriter writer, @Nullable final Set<T> value) throws IOException {
            if (value == null) {
                return;
            }

            StringBuilder result = new StringBuilder();

            for (final T item : value) {
                result.append(ProxerUtils.getSafeApiEnumName(item));
                result.append(delimiter);
            }

            if (result.length() > 0) {
                result = new StringBuilder(result.substring(0, result.length() - delimiter.length()));
            }

            writer.value(result.toString());
        }

        private List<String> parseParts(final JsonReader reader) throws IOException {
            final JsonReader.Token nextToken = reader.peek();

            if (nextToken == JsonReader.Token.BEGIN_ARRAY) {
                final List<String> result = new ArrayList<>();

                reader.beginArray();

                while (reader.hasNext()) {
                    result.add(reader.nextString());
                }

                reader.endArray();

                return result;
            } else if (nextToken == JsonReader.Token.NULL) {
                reader.nextNull();

                return Collections.emptyList();
            } else {
                final String value = reader.nextString();

                if (value.isEmpty()) {
                    return Collections.emptyList();
                } else {
                    return Arrays.asList(value.split(delimiter));
                }
            }
        }

        private Set<T> convertToEnumConstants(final JsonReader reader, final List<String> parts) {
            final EnumSet<T> result = EnumSet.noneOf(enumType);

            for (final String part : parts) {
                final T[] constants = enumType.getEnumConstants();
                final String[] nameStrings = new String[constants.length];
                T value = null;

                try {
                    for (int i = 0; i < constants.length; i++) {
                        final T constant = constants[i];
                        final Json annotation = enumType.getField(constant.name()).getAnnotation(Json.class);
                        final String name = annotation != null ? annotation.name() : constant.name();
                        nameStrings[i] = name;

                        if (name.equalsIgnoreCase(part)) {
                            value = constant;
                        }
                    }
                } catch (NoSuchFieldException e) {
                    throw new AssertionError("Missing field in " + enumType.getName(), e);
                }

                if (value == null) {
                    final FallbackEnum fallbackAnnotation = enumType.getAnnotation(FallbackEnum.class);

                    if (fallbackAnnotation != null) {
                        value = Enum.valueOf(enumType, fallbackAnnotation.name());
                    } else {
                        throw new JsonDataException("Expected one of " + Arrays.asList(nameStrings)
                                + " but was " + part + " at path " + reader.getPath());
                    }
                }

                result.add(value);
            }

            return result;
        }
    }
}
