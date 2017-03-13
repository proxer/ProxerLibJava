package com.proxerme.library.api;

import com.proxerme.library.entitiy.manga.Page;
import com.squareup.moshi.JsonDataException;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Ruben Gees
 */
public class PageAdapterTest {

    private PageAdapter adapter;

    @Before
    public void setUp() {
        adapter = new PageAdapter();
    }

    @Test
    public void testFromJson() {
        assertThat(adapter.fromJson(new String[][]{
                new String[]{"a", "1", "2"},
                new String[]{"b", "5", "3"}
        })).containsExactly(new Page("a", 1, 2), new Page("b", 5, 3));
    }

    @Test
    public void testFromJsonInvalidSize() {
        assertThatExceptionOfType(JsonDataException.class).isThrownBy(() -> adapter.fromJson(new String[][]{
                new String[]{"a", "1", "2"},
                new String[0]
        }));
    }

    @Test
    public void testFromJsonNoNumber() {
        assertThatExceptionOfType(NumberFormatException.class).isThrownBy(() -> adapter.fromJson(new String[][]{
                new String[]{"a", "invalid", "2"}
        }));
    }
}
