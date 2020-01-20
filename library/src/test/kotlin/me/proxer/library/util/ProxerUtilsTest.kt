package me.proxer.library.util

import me.proxer.library.enums.AnimeLanguage
import me.proxer.library.enums.LengthBound
import me.proxer.library.enums.MediaLanguage
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class ProxerUtilsTest {

    @Test
    fun testGetEnumName() {
        ProxerUtils.getApiEnumName(MediaLanguage.GERMAN_SUB) shouldBeEqualTo "gersub"
    }

    @Test
    fun testGetSafeEnumName() {
        ProxerUtils.getApiEnumName(LengthBound.HIGHER) shouldBeEqualTo "up"
    }

    @Test
    fun testToApiEnum() {
        ProxerUtils.toApiEnum<MediaLanguage>("gersub") shouldBeEqualTo MediaLanguage.GERMAN_SUB
    }

    @Test
    fun testToSafeApiEnum() {
        ProxerUtils.toSafeApiEnum<AnimeLanguage>("gersub") shouldBeEqualTo AnimeLanguage.GERMAN_SUB
    }

    @Test
    fun testToApiEnumIgnoresCase() {
        ProxerUtils.toApiEnum<MediaLanguage>("ENGSUB") shouldBeEqualTo MediaLanguage.ENGLISH_SUB
    }

    @Test
    fun testToApiEnumInvalidString() {
        ProxerUtils.toApiEnum<MediaLanguage>("invalid").shouldBeNull()
    }
}
