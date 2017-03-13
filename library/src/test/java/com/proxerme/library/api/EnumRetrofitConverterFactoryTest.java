package com.proxerme.library.api;

import com.proxerme.library.entitiy.info.Entry;
import com.proxerme.library.enums.Genre;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.annotation.Annotation;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * @author Ruben Gees
 */
public class EnumRetrofitConverterFactoryTest {

    private EnumRetrofitConverterFactory factory;

    @Before
    public void setUp() {
        factory = new EnumRetrofitConverterFactory();
    }

    @Test
    public void testCreate() {
        assertThat(factory.stringConverter(Genre.class, new Annotation[0], null)).isNotNull();
    }

    @Test
    public void testCreateNoEnum() {
        assertThat(factory.stringConverter(Entry.class, new Annotation[0], null)).isNull();
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    @Test
    public void testConvert() throws IOException {
        assertThat(((Converter<Enum<?>, String>) factory.stringConverter(Genre.class, new Annotation[0], null))
                .convert(Genre.ADVENTURE)).isEqualTo("Abenteuer");
    }
}
