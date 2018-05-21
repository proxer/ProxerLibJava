package me.proxer.library.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;

import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * @author Ruben Gees
 */
class BooleanAdapterFactory implements JsonAdapter.Factory {

    @Nullable
    @Override
    public JsonAdapter<?> create(final Type type, final Set<? extends Annotation> annotations, final Moshi moshi) {
        if (type != boolean.class && type != Boolean.class) {
            return null;
        }

        return new BooleanAdapter();
    }

    private static class BooleanAdapter extends JsonAdapter<Boolean> {

        @Nullable
        @Override
        public Boolean fromJson(final JsonReader reader) throws IOException {
            final JsonReader.Token nextToken = reader.peek();

            if (nextToken == JsonReader.Token.BOOLEAN) {
                return reader.nextBoolean();
            } else if (nextToken == JsonReader.Token.NUMBER) {
                final int number = reader.nextInt();

                return intToBoolean(number, reader.getPath());
            } else if (nextToken == JsonReader.Token.STRING) {
                final String jsonString = reader.nextString();
                final int jsonNumber = toIntOrNull(jsonString);

                if (jsonNumber < 0) {
                    return stringToBoolean(jsonString, reader.getPath());
                } else {
                    return intToBoolean(jsonNumber, reader.getPath());
                }
            } else if (nextToken == JsonReader.Token.NULL) {
                return reader.nextNull();
            } else {
                throw new JsonDataException("Expected a boolean, number, string or null but was " + nextToken.name()
                        + " at path " + reader.getPath());
            }
        }

        @Override
        public void toJson(final JsonWriter writer, @Nullable final Boolean value) throws IOException {
            writer.value(value);
        }

        private int toIntOrNull(final String candidate) {
            try {
                return Integer.parseInt(candidate);
            } catch (NumberFormatException ignored) {
                return -1;
            }
        }

        private boolean intToBoolean(final int subject, final String path) {
            switch (subject) {
                case 0:
                    return false;
                case 1:
                    return true;
                default:
                    throw new JsonDataException("Expected a 1 or 0 but was " + subject + " at path " + path);
            }
        }

        private boolean stringToBoolean(final String subject, final String path) {
            switch (subject) {
                case "true":
                    return true;
                case "false":
                    return false;
                default:
                    throw new JsonDataException("Expected a true or false but was " + subject + " at path " + path);
            }
        }
    }
}
