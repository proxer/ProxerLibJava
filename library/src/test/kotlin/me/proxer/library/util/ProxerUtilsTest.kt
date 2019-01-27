package me.proxer.library.util

import me.proxer.library.enums.AnimeLanguage
import me.proxer.library.enums.LengthBound
import me.proxer.library.enums.MediaLanguage
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class ProxerUtilsTest {

    @Test
    fun testGetEnumName() {
        assertThat(ProxerUtils.getApiEnumName(MediaLanguage.GERMAN_SUB)).isEqualTo("gersub")
    }

    @Test
    fun testGetSafeEnumName() {
        assertThat(ProxerUtils.getApiEnumName(LengthBound.HIGHER)).isEqualTo("up")
    }

    @Test
    fun testToApiEnum() {
        assertThat(ProxerUtils.toApiEnum<MediaLanguage>("gersub")).isEqualTo(MediaLanguage.GERMAN_SUB)
    }

    @Test
    fun testToSafeApiEnum() {
        assertThat(ProxerUtils.toSafeApiEnum<AnimeLanguage>("gersub")).isEqualTo(AnimeLanguage.GERMAN_SUB)
    }

    @Test
    fun testToApiEnumIgnoresCase() {
        assertThat(ProxerUtils.toApiEnum<MediaLanguage>("ENGSUB")).isEqualTo(MediaLanguage.ENGLISH_SUB)
    }

    @Test
    fun testToApiEnumInvalidString() {
        assertThat(ProxerUtils.toApiEnum<MediaLanguage>("invalid")).isNull()
    }
}
