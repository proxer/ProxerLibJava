package me.proxer.library.internal.adapter

import me.proxer.library.entity.messenger.Conference
import me.proxer.library.internal.adapter.ConferenceAdapter.IntermediateConference
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class ConferenceAdapterTest {

    private lateinit var adapter: ConferenceAdapter

    @BeforeEach
    fun setUp() {
        adapter = ConferenceAdapter()
    }

    @Test
    fun testFromJson() {
        assertThat(adapter.fromJson(constructIntermediateConference(true)))
            .isEqualTo(constructConference(true))
    }

    @Test
    fun testFromJsonWithNoImage() {
        assertThat(adapter.fromJson(constructIntermediateConference(false)))
            .isEqualTo(constructConference(false))
    }

    private fun constructIntermediateConference(withImage: Boolean): IntermediateConference {
        return IntermediateConference(
            "123", "something", "somethingElse", 13, true, false,
            Date(123456L), 2, "321",
            if (withImage) "avatar:http://example.com/image.png" else ""
        )
    }

    private fun constructConference(withImage: Boolean): Conference {
        return Conference(
            "123", "something", "somethingElse", 13,
            if (withImage) "http://example.com/image.png" else "", if (withImage) "avatar" else "", true, false,
            Date(123456L), 2, "321"
        )
    }
}
