package me.proxer.library.api

import com.squareup.moshi.Types
import me.proxer.library.entity.notifications.NewsArticle
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import retrofit2.Retrofit

/**
 * @author Ruben Gees
 */
class ProxerResponseCallAdapterFactoryTest {

    private val factory = ProxerResponseCallAdapterFactory()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://example.com")
        .build()

    @Test
    fun testGet() {
        val proxerCallType = Types.newParameterizedType(ProxerCall::class.java, NewsArticle::class.java)

        assertThat(factory.get(proxerCallType, emptyArray(), retrofit)).isNotNull
    }

    @Test
    fun testGetInvalidType() {
        val listType = Types.newParameterizedType(List::class.java, NewsArticle::class.java)

        assertThat(factory.get(listType, emptyArray(), retrofit)).isNull()
    }

    @Test
    fun testGetNoParameter() {
        assertThat(factory.get(List::class.java, emptyArray(), retrofit)).isNull()
    }
}
