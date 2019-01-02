package me.proxer.library.api;

import me.proxer.library.entity.info.Entry;
import me.proxer.library.enums.MediaLanguage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * @author Ruben Gees
 */
class EnumRetrofitConverterFactoryTest {

    private EnumRetrofitConverterFactory factory;
    private Retrofit retrofit;

    @BeforeEach
    void setUp() {
        factory = new EnumRetrofitConverterFactory();
        retrofit = new Retrofit.Builder().baseUrl("https://proxer.me").build();
    }

    @Test
    void testCreate() {
        assertThat(factory.stringConverter(MediaLanguage.class, new Annotation[0], retrofit)).isNotNull();
    }

    @Test
    void testCreateNoEnum() {
        assertThat(factory.stringConverter(Entry.class, new Annotation[0], retrofit)).isNull();
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    @Test
    void testConvert() throws IOException {
        final Converter<Enum<?>, String> converter = (Converter<Enum<?>, String>)
                factory.stringConverter(MediaLanguage.class, new Annotation[0], retrofit);

        assertThat(converter.convert(MediaLanguage.GERMAN)).isEqualTo("de");
    }
}
