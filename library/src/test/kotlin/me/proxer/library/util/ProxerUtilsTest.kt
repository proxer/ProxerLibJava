package me.proxer.library.util

import me.proxer.library.enums.AnimeLanguage
import me.proxer.library.enums.LengthBound
import me.proxer.library.enums.MediaLanguage
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class ProxerUtilsTest {

    @Test
    fun testGetEnumName() {
        ProxerUtils.getApiEnumName(MediaLanguage.GERMAN_SUB) shouldEqual "gersub"
    }

    @Test
    fun testGetSafeEnumName() {
        ProxerUtils.getApiEnumName(LengthBound.HIGHER) shouldEqual "up"
    }

    @Test
    fun testToApiEnum() {
        ProxerUtils.toApiEnum<MediaLanguage>("gersub") shouldEqual MediaLanguage.GERMAN_SUB
    }

    @Test
    fun testToSafeApiEnum() {
        ProxerUtils.toSafeApiEnum<AnimeLanguage>("gersub") shouldEqual AnimeLanguage.GERMAN_SUB
    }

    @Test
    fun testToApiEnumIgnoresCase() {
        ProxerUtils.toApiEnum<MediaLanguage>("ENGSUB") shouldEqual MediaLanguage.ENGLISH_SUB
    }

    @Test
    fun testToApiEnumInvalidString() {
        ProxerUtils.toApiEnum<MediaLanguage>("invalid").shouldBeNull()
    }
}
