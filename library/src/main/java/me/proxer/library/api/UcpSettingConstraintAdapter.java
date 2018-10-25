package me.proxer.library.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;
import com.squareup.moshi.ToJson;
import me.proxer.library.enums.UcpSettingConstraint;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Ruben Gees
 */
class UcpSettingConstraintAdapter {

    private final Map<Integer, UcpSettingConstraint> nameToValue;
    private final Map<UcpSettingConstraint, Integer> valueToName;

    UcpSettingConstraintAdapter() {
        final Field[] fields = UcpSettingConstraint.class.getFields();

        nameToValue = new LinkedHashMap<>(fields.length);
        valueToName = new LinkedHashMap<>(fields.length);

        for (final Field field : fields) {
            final Json annotation = field.getAnnotation(Json.class);

            if (annotation != null) {
                final int name = Integer.parseInt(annotation.name());
                final UcpSettingConstraint value = UcpSettingConstraint.valueOf(field.getName());

                nameToValue.put(name, value);
                valueToName.put(value, name);
            }
        }
    }

    @ToJson
    int toJson(final UcpSettingConstraint constraint) {
        return valueToName.get(constraint);
    }

    @FromJson
    UcpSettingConstraint fromJson(final int value) {
        return nameToValue.get(value);
    }
}
