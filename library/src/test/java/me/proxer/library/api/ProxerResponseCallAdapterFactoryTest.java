package me.proxer.library.api;

import com.squareup.moshi.Types;
import me.proxer.library.entitiy.notifications.NewsArticle;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * @author Ruben Gees
 */
public class ProxerResponseCallAdapterFactoryTest {

    private ProxerResponseCallAdapterFactory factory;
    private Retrofit retrofit;

    @Before
    public void setUp() {
        factory = new ProxerResponseCallAdapterFactory();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://example.com")
                .build();
    }

    @Test
    public void testGet() {
        assertThat(factory.get(Types.newParameterizedType(ProxerCall.class, NewsArticle.class),
                new Annotation[0], retrofit)).isNotNull();
    }

    @Test
    public void testGetInvalidType() {
        assertThat(factory.get(Types.newParameterizedType(List.class, NewsArticle.class),
                new Annotation[0], retrofit)).isNull();
    }

    @Test
    public void testGetNoParameter() {
        assertThat(factory.get(List.class, new Annotation[0], retrofit)).isNull();
    }
}
