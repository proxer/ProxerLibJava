package me.proxer.library.internal.adapter

import com.squareup.moshi.JsonDataException
import me.proxer.library.entity.manga.Page
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class PageAdapterTest {

    private val adapter = PageAdapter()

    @Test
    fun testFromJson() {
        adapter.fromJson(arrayOf(arrayOf("a", "1", "2"), arrayOf("b", "5", "3"))) shouldBeEqualTo listOf(
            Page(name = "a", height = 1, width = 2),
            Page(name = "b", height = 5, width = 3)
        )
    }

    @Test
    fun testFromJsonInvalidSize() {
        invoking { adapter.fromJson(arrayOf(arrayOf("a", "1"))) } shouldThrow JsonDataException::class
    }

    @Test
    fun testFromJsonNoNumber() {
        invoking { adapter.fromJson(arrayOf(arrayOf("a", "invalid", "2"))) } shouldThrow JsonDataException::class
    }
}
