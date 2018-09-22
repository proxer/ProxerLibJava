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
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
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
            final String parts = parseParts(reader);

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

        private String parseParts(final JsonReader reader) throws IOException {
            final JsonReader.Token nextToken = reader.peek();

            if (nextToken == JsonReader.Token.BEGIN_ARRAY) {
                final List<String> result = new ArrayList<>();

                reader.beginArray();

                while (reader.hasNext()) {
                    result.add(reader.nextString());
                }

                reader.endArray();

                return ProxerUtils.join(delimiter, result);
            } else if (nextToken == JsonReader.Token.NULL) {
                reader.nextNull();

                return "";
            } else {
                return reader.nextString();
            }
        }

        private Set<T> convertToEnumConstants(final JsonReader reader, final String parts) {
            final EnumSet<T> result = EnumSet.noneOf(enumType);

            String currentParts = parts.toLowerCase(Locale.US);

            while (currentParts.length() > 0) {
                boolean found = false;

                for (int i = 0; i < enumNames.length; i++) {
                    final String name = enumNames[i];

                    if (currentParts.startsWith(name)) {
                        final boolean isLast = currentParts.length() == name.length();
                        final boolean matches = isLast || currentParts.startsWith(delimiter, name.length());

                        if (matches) {
                            result.add(enumConstants[i]);

                            if (isLast) {
                                currentParts = "";
                            } else {
                                currentParts = currentParts.substring(name.length() + delimiter.length());
                            }

                            found = true;
                            break;
                        }
                    }
                }

                if (!found) {
                    final int nextDelimiter = currentParts.indexOf(delimiter);

                    if (fallbackEnum != null) {
                        result.add(fallbackEnum);

                        if (nextDelimiter >= 0 && currentParts.length() > nextDelimiter + delimiter.length()) {
                            currentParts = currentParts.substring(nextDelimiter + delimiter.length());
                        } else {
                            currentParts = "";
                        }
                    } else {
                        final String part;

                        if (nextDelimiter >= 0) {
                            part = currentParts.substring(0, nextDelimiter);
                        } else {
                            part = currentParts;
                        }

                        throw new JsonDataException("Expected one of " + Arrays.asList(enumNames)
                                + " but was " + part + " at path " + reader.getPath());
                    }
                }
            }

            return result;
        }
    }
}
