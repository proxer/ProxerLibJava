package me.proxer.library.api;

import me.proxer.library.entity.info.Entry;
import me.proxer.library.enums.Genre;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * @author Ruben Gees
 */
public class EnumRetrofitConverterFactoryTest {

    private EnumRetrofitConverterFactory factory;
    private Retrofit retrofit;

    @Before
    public void setUp() {
        factory = new EnumRetrofitConverterFactory();
        retrofit = new Retrofit.Builder().baseUrl("https://proxer.me").build();
    }

    @Test
    public void testCreate() {
        assertThat(factory.stringConverter(Genre.class, new Annotation[0], retrofit)).isNotNull();
    }

    @Test
    public void testCreateNoEnum() {
        assertThat(factory.stringConverter(Entry.class, new Annotation[0], retrofit)).isNull();
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    @Test
    public void testConvert() throws IOException {
        assertThat(((Converter<Enum<?>, String>) factory.stringConverter(Genre.class, new Annotation[0], retrofit))
                .convert(Genre.ADVENTURE)).isEqualTo("Adventure");
    }
}
