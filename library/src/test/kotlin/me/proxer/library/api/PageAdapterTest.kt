package me.proxer.library.api

import com.squareup.moshi.JsonDataException
import me.proxer.library.entity.manga.Page
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class PageAdapterTest {

    private val adapter = PageAdapter()

    @Test
    fun testFromJson() {
        Assertions.assertThat(adapter.fromJson(arrayOf(arrayOf("a", "1", "2"), arrayOf("b", "5", "3"))))
            .containsExactly(Page("a", 1, 2), Page("b", 5, 3))
    }

    @Test
    fun testFromJsonInvalidSize() {
        assertThatExceptionOfType(JsonDataException::class.java).isThrownBy {
            adapter.fromJson(arrayOf(arrayOf("a", "1", "2"), emptyArray()))
        }
    }

    @Test
    fun testFromJsonNoNumber() {
        assertThatExceptionOfType(NumberFormatException::class.java).isThrownBy {
            adapter.fromJson(arrayOf(arrayOf("a", "invalid", "2")))
        }
    }
}
