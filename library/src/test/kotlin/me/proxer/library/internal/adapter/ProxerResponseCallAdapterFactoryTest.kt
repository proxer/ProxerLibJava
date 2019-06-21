package me.proxer.library.internal.adapter

import com.squareup.moshi.Types
import me.proxer.library.ProxerCall
import me.proxer.library.entity.notifications.NewsArticle
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldNotBeNull
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

        factory.get(proxerCallType, emptyArray(), retrofit).shouldNotBeNull()
    }

    @Test
    fun testGetInvalidType() {
        val listType = Types.newParameterizedType(List::class.java, NewsArticle::class.java)

        factory.get(listType, emptyArray(), retrofit).shouldBeNull()
    }

    @Test
    fun testGetNoParameter() {
        factory.get(List::class.java, emptyArray(), retrofit).shouldBeNull()
    }
}
