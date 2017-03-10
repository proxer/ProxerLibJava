package com.proxerme.library.api;

import com.proxerme.library.entitiy.notifications.NewsArticle;
import com.squareup.moshi.Types;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * @author Ruben Gees
 */
public class ProxerResponseCallAdapterFactoryTest {

    private ProxerResponseCallAdapterFactory factory;

    @Before
    public void setUp() {
        factory = new ProxerResponseCallAdapterFactory();
    }

    @Test
    public void testGet() {
        assertThat(factory.get(Types.newParameterizedType(ProxerCall.class, NewsArticle.class),
                new Annotation[0], null)).isNotNull();
    }

    @Test
    public void testGetInvalidType() {
        assertThat(factory.get(Types.newParameterizedType(List.class, NewsArticle.class),
                new Annotation[0], null)).isNull();
    }

    @Test
    public void testGetNoParameter() {
        assertThat(factory.get(List.class, new Annotation[0], null)).isNull();
    }
}
