package me.proxer.library.api;

import okhttp3.HttpUrl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class HttpUrlAdapterTest {

    private HttpUrlAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new HttpUrlAdapter();
    }

    @Test
    void testNormal() {
        assertThat(adapter.fromJson("https://www.example.com")).isEqualTo(HttpUrl.parse("https://www.example.com"));
    }

    @Test
    void testSchemaLess() {
        assertThat(adapter.fromJson("//www.example.com")).isEqualTo(HttpUrl.parse("http://www.example.com"));
    }

    @Test
    void testInvalid() {
        assertThat(adapter.fromJson("example.com")).isNull();
    }
}
