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
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.util.ProxerUtils;

import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

/**
 * @author Ruben Gees
 */
class DelimitedEnumSetAdapterFactory implements JsonAdapter.Factory {

    private static final String DELIMITER = " ";
    private static final String COMMA_DELIMITER = ",";

    @SuppressWarnings("Duplicates")
    @Nullable
    @Override
    public JsonAdapter<?> create(final Type type, final Set<? extends Annotation> annotations, final Moshi moshi) {
        final Type rawType = Types.getRawType(type);

        if (rawType != Set.class || !(type instanceof ParameterizedType)) {
            return null;
        }

        final Type parameterType = ((ParameterizedType) type).getActualTypeArguments()[0];

        if (parameterType == FskConstraint.class) {
            return new DelimitedEnumSetAdapter<>(FskConstraint.class, DELIMITER);
        } else if (parameterType == MediaLanguage.class) {
            return new DelimitedEnumSetAdapter<>(MediaLanguage.class, COMMA_DELIMITER);
        }

        return null;
    }

    private static class DelimitedEnumSetAdapter<T extends Enum<T>> extends JsonAdapter<Set<T>> {

        private final Class<T> enumType;
        private final String delimiter;

        private final T[] enumConstants;
        private final String[] enumNames;
        private final T fallbackEnum;

        DelimitedEnumSetAdapter(final Class<T> enumType, final String delimiter) {
            this.enumType = enumType;
            this.delimiter = delimiter;

            this.enumConstants = enumType.getEnumConstants();
            this.enumNames = new String[this.enumConstants.length];

            for (int i = 0; i < this.enumConstants.length; i++) {
                final T constant = this.enumConstants[i];

                try {
                    final Json annotation = enumType.getField(constant.name()).getAnnotation(Json.class);
                    final String name = annotation != null ? annotation.name() : constant.name();

                    this.enumNames[i] = name.toLowerCase(Locale.US);
                } catch (NoSuchFieldException e) {
                    throw new AssertionError("Missing field in " + enumType.getName(), e);
                }
            }

            final FallbackEnum fallbackAnnotation = enumType.getAnnotation(FallbackEnum.class);

            if (fallbackAnnotation != null) {
                fallbackEnum = Enum.valueOf(enumType, fallbackAnnotation.name());
            } else {
                fallbackEnum = null;
            }
        }

        @Override
        public Set<T> fromJson(final JsonReader reader) throws IOException {
            final Set<String> parts = parseParts(reader);

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

        private Set<String> parseParts(final JsonReader reader) throws IOException {
            final JsonReader.Token nextToken = reader.peek();

            if (nextToken == JsonReader.Token.BEGIN_ARRAY) {
                final Set<String> result = new LinkedHashSet<>();

                reader.beginArray();

                while (reader.hasNext()) {
                    result.add(reader.nextString().trim().toLowerCase(Locale.US));
                }

                reader.endArray();

                return result;
            } else if (nextToken == JsonReader.Token.NULL) {
                reader.nextNull();

                return Collections.emptySet();
            } else {
                final String rawParts = reader.nextString().trim().toLowerCase(Locale.US);

                if (rawParts.isEmpty()) {
                    return Collections.emptySet();
                } else {
                    final String[] splitParts = rawParts.split(delimiter);

                    return new HashSet<>(Arrays.asList(splitParts));
                }
            }
        }

        private Set<T> convertToEnumConstants(final JsonReader reader, final Set<String> parts) {
            final EnumSet<T> result = EnumSet.noneOf(enumType);

            for (final String part : parts) {
                boolean found = false;

                for (int i = 0; i < enumNames.length; i++) {
                    final String name = enumNames[i];

                    if (part.equals(name)) {
                        result.add(enumConstants[i]);

                        found = true;
                        break;
                    }
                }

                if (!found) {
                    if (fallbackEnum != null) {
                        result.add(fallbackEnum);
                    } else {
                        throw new JsonDataException("Expected one of " + Arrays.asList(enumNames)
                                + " but was " + part + " at path " + reader.getPath());
                    }
                }
            }

            return result;
        }
    }
}
