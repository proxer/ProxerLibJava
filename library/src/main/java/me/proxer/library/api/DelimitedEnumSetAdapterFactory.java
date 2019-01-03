package me.proxer.library.api;

import com.serjltt.moshi.adapters.FallbackEnum;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import me.proxer.library.util.ProxerUtils;

import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;

/**
 * @author Ruben Gees
 */
class DelimitedEnumSetAdapterFactory implements JsonAdapter.Factory {

    @SuppressWarnings({"Duplicates", "unchecked"})
    @Nullable
    @Override
    public JsonAdapter<?> create(final Type type, final Set<? extends Annotation> annotations, final Moshi moshi) {
        final Type rawType = Types.getRawType(type);

        if (rawType != Set.class || !(type instanceof ParameterizedType)) {
            return null;
        }

        final Type parameterType = ((ParameterizedType) type).getActualTypeArguments()[0];

        if (!(parameterType instanceof Class<?>) || !((Class) parameterType).isEnum()) {
            return null;
        }

        final DelimitedEnumSet annotation = findAnnotation(annotations);

        if (annotation == null) {
            return null;
        }

        return new DelimitedEnumSetAdapter((Class<?>) parameterType, annotation.delimiter());
    }

    @Nullable
    private DelimitedEnumSet findAnnotation(final Set<? extends Annotation> annotations) {
        for (final Annotation annotation : annotations) {
            if (annotation.annotationType() == DelimitedEnumSet.class) {
                return (DelimitedEnumSet) annotation;
            }
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
            if (reader.peek() == JsonReader.Token.NULL) {
                reader.nextNull();

                return Collections.emptySet();
            } else {
                final String rawParts = reader.nextString().trim().toLowerCase(Locale.US);

                if (rawParts.isEmpty()) {
                    return Collections.emptySet();
                } else {
                    final String[] splitParts = rawParts.split(delimiter);

                    return convertToEnumConstants(reader, splitParts);
                }
            }
        }

        @Override
        public void toJson(final JsonWriter writer, @Nullable final Set<T> value) throws IOException {
            writer.value(value == null ? null : ProxerUtils.joinEnums(delimiter, EnumSet.copyOf(value)));
        }

        private Set<T> convertToEnumConstants(final JsonReader reader, final String... parts) {
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
