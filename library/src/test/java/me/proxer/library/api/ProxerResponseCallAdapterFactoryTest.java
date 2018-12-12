package me.proxer.library.api;

import com.squareup.moshi.Types;
import me.proxer.library.entity.notifications.NewsArticle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * @author Ruben Gees
 */
class ProxerResponseCallAdapterFactoryTest {

    private ProxerResponseCallAdapterFactory factory;
    private Retrofit retrofit;

    @BeforeEach
    void setUp() {
        factory = new ProxerResponseCallAdapterFactory();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://example.com")
                .build();
    }

    @Test
    void testGet() {
        assertThat(factory.get(Types.newParameterizedType(ProxerCall.class, NewsArticle.class),
                new Annotation[0], retrofit)).isNotNull();
    }

    @Test
    void testGetInvalidType() {
        assertThat(factory.get(Types.newParameterizedType(List.class, NewsArticle.class),
                new Annotation[0], retrofit)).isNull();
    }

    @Test
    void testGetNoParameter() {
        assertThat(factory.get(List.class, new Annotation[0], retrofit)).isNull();
    }
}
