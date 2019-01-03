package me.proxer.library.api;

import com.squareup.moshi.JsonAdapter;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ruben Gees
 */
class DelimitedStringSetAdapterFactory implements JsonAdapter.Factory {

    @SuppressWarnings("Duplicates")
    @Nullable
    @Override
    public JsonAdapter<?> create(final Type type, final Set<? extends Annotation> annotations, final Moshi moshi) {
        final Type rawType = Types.getRawType(type);

        if (rawType != Set.class || !(type instanceof ParameterizedType)) {
            return null;
        }

        final Type parameterType = ((ParameterizedType) type).getActualTypeArguments()[0];

        if (parameterType != String.class) {
            return null;
        }

        final DelimitedStringSet annotation = findAnnotation(annotations);

        if (annotation == null) {
            return null;
        }

        return new DelimitedStringSetAdapter(annotation.delimiter(), annotation.valuesToKeep());
    }

    @Nullable
    private DelimitedStringSet findAnnotation(final Set<? extends Annotation> annotations) {
        for (final Annotation annotation : annotations) {
            if (annotation.annotationType() == DelimitedStringSet.class) {
                return (DelimitedStringSet) annotation;
            }
        }

        return null;
    }

    private static class DelimitedStringSetAdapter extends JsonAdapter<Set<String>> {

        private final String delimiter;
        private final List<Set<String>> valuesToKeep;

        DelimitedStringSetAdapter(final String delimiter, final String... valuesToKeep) {
            this.delimiter = delimiter;
            this.valuesToKeep = new ArrayList<>();

            for (final String valueToKeep : valuesToKeep) {
                final String[] splitValueToKeep = valueToKeep.split(delimiter);

                this.valuesToKeep.add(new LinkedHashSet<>(Arrays.asList(splitValueToKeep)));
            }
        }

        @Override
        public Set<String> fromJson(final JsonReader reader) throws IOException {
            if (reader.peek() == JsonReader.Token.NULL) {
                reader.nextNull();

                return Collections.emptySet();
            } else {
                final String rawParts = reader.nextString().trim();

                if (rawParts.isEmpty()) {
                    return Collections.emptySet();
                } else {
                    final String[] splitParts = rawParts.split(delimiter);
                    final Set<String> result = new HashSet<>(Arrays.asList(splitParts));

                    for (final Set<String> valueToKeep : valuesToKeep) {
                        if (result.removeAll(valueToKeep)) {
                            result.add(ProxerUtils.join(delimiter, valueToKeep));
                        }
                    }

                    return result;
                }
            }
        }

        @Override
        public void toJson(final JsonWriter writer, @Nullable final Set<String> value) throws IOException {
            writer.value(value == null ? null : ProxerUtils.join(delimiter, value));
        }
    }
}
