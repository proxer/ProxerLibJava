package me.proxer.library.api;

import okhttp3.HttpUrl;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class HttpUrlAdapterTest {

    private HttpUrlAdapter adapter;

    @Before
    public void setUp() throws Exception {
        adapter = new HttpUrlAdapter();
    }

    @Test
    public void testNormal() throws Exception {
        assertThat(adapter.fromJson("https://www.example.com")).isEqualTo(HttpUrl.parse("https://www.example.com"));
    }

    @Test
    public void testSchemaLess() throws Exception {
        assertThat(adapter.fromJson("//www.example.com")).isEqualTo(HttpUrl.parse("http://www.example.com"));
    }

    @Test
    public void testInvalid() throws Exception {
        assertThat(adapter.fromJson("example.com")).isNull();
    }
}
