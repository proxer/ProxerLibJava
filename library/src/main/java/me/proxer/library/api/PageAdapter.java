package me.proxer.library.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonDataException;
import me.proxer.library.entitiy.manga.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruben Gees
 */
final class PageAdapter {

    private static final int FIELD_AMOUNT = 3;

    private static final int NAME_FIELD_LOCATION = 0;
    private static final int HEIGHT_FIELD_LOCATION = 1;
    private static final int WIDTH_FIELD_LOCATION = 2;

    @FromJson
    List<Page> fromJson(final String[][] json) {
        final List<Page> result = new ArrayList<>();

        for (String[] jsonPage : json) {
            if (jsonPage.length != FIELD_AMOUNT) {
                throw new JsonDataException("Page array length is " + json.length + " instead of 3.");
            }

            result.add(new Page(jsonPage[NAME_FIELD_LOCATION], Integer.parseInt(jsonPage[HEIGHT_FIELD_LOCATION]),
                    Integer.parseInt(jsonPage[WIDTH_FIELD_LOCATION])));
        }

        return result;
    }
}
