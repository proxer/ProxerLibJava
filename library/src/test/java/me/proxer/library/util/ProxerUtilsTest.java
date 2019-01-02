package me.proxer.library.util;

import com.pushtorefresh.private_constructor_checker.PrivateConstructorChecker;
import me.proxer.library.enums.AnimeLanguage;
import me.proxer.library.enums.Gender;
import me.proxer.library.enums.LengthBound;
import me.proxer.library.enums.MediaLanguage;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class ProxerUtilsTest {

    @Test
    void testGetEnumName() {
        assertThat(ProxerUtils.getApiEnumName(MediaLanguage.GERMAN_SUB)).isEqualTo("gersub");
    }

    @Test
    void testGetSafeEnumName() {
        assertThat(ProxerUtils.getApiEnumName(LengthBound.HIGHER)).isEqualTo("up");
    }

    @Test
    void testToApiEnum() {
        assertThat(ProxerUtils.toApiEnum(MediaLanguage.class, "gersub")).isEqualTo(MediaLanguage.GERMAN_SUB);
    }

    @Test
    void testToSafeApiEnum() {
        assertThat(ProxerUtils.toSafeApiEnum(AnimeLanguage.class, "gersub")).isEqualTo(AnimeLanguage.GERMAN_SUB);
    }

    @Test
    void testToApiEnumIgnoresCase() {
        assertThat(ProxerUtils.toApiEnum(MediaLanguage.class, "ENGSUB")).isEqualTo(MediaLanguage.ENGLISH_SUB);
    }

    @Test
    void testToApiEnumInvalidString() {
        assertThat(ProxerUtils.toApiEnum(MediaLanguage.class, "invalid")).isNull();
    }

    @Test
    void testJoin() {
        assertThat(ProxerUtils.join(";", Arrays.asList("a", "b", "c"))).isEqualTo("a;b;c");
    }

    @Test
    void testJoinEmpty() {
        assertThat(ProxerUtils.join(";", Collections.emptyList())).isEmpty();
    }

    @Test
    void testJoinEnums() {
        assertThat(ProxerUtils.joinEnums(";", EnumSet.of(Gender.MALE, Gender.FEMALE, Gender.OTHER)))
                .isEqualTo("m;f;o");
    }

    @Test
    void testJoinEnumsEmpty() {
        assertThat(ProxerUtils.joinEnums(";", EnumSet.noneOf(Gender.class))).isEmpty();
    }

    @Test
    void testIsBlank() {
        assertThat(ProxerUtils.isBlank("   ")).isTrue();
    }

    @Test
    void testIsBlankEmpty() {
        assertThat(ProxerUtils.isBlank("")).isTrue();
    }

    @Test
    void testIsBlankFalse() {
        assertThat(ProxerUtils.isBlank(" a ")).isFalse();
        assertThat(ProxerUtils.isBlank(" a")).isFalse();
        assertThat(ProxerUtils.isBlank("a ")).isFalse();
    }

    @Test
    void testIsUtilityClass() {
        PrivateConstructorChecker
                .forClass(ProxerUtils.class)
                .expectedTypeOfException(UnsupportedOperationException.class)
                .check();
    }
}
