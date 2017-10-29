package me.proxer.library.util;

import com.pushtorefresh.private_constructor_checker.PrivateConstructorChecker;
import me.proxer.library.enums.Genre;
import me.proxer.library.enums.MediaLanguage;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class ProxerUtilsTest {

    @Test
    public void testGetEnumName() throws NoSuchFieldException {
        assertThat(ProxerUtils.getApiEnumName(Genre.COMEDY)).isEqualTo("Comedy");
    }

    @Test
    public void testToApiEnum() {
        assertThat(ProxerUtils.toApiEnum(MediaLanguage.class, "gersub")).isEqualTo(MediaLanguage.GERMAN_SUB);
    }

    @Test
    public void testToApiEnumIgnoresCase() throws Exception {
        assertThat(ProxerUtils.toApiEnum(Genre.class, "ACTION")).isEqualTo(Genre.ACTION);
    }

    @Test
    public void testToApiEnumInvalidString() {
        assertThat(ProxerUtils.toApiEnum(MediaLanguage.class, "invalid")).isNull();
    }

    @Test
    public void testJoin() {
        assertThat(ProxerUtils.join(";", Arrays.asList("a", "b", "c"))).isEqualTo("a;b;c");
    }

    @Test
    public void testJoinEmpty() {
        assertThat(ProxerUtils.join(";", Collections.emptyList())).isEmpty();
    }

    @Test
    public void testJoinEnums() {
        assertThat(ProxerUtils.joinEnums(";", EnumSet.of(Genre.ADVENTURE, Genre.ACTION, Genre.DRAMA)))
                .isEqualTo("Abenteuer;Action;Drama");
    }

    @Test
    public void testJoinEnumsEmpty() {
        assertThat(ProxerUtils.joinEnums(";", EnumSet.noneOf(Genre.class))).isEmpty();
    }

    @Test
    public void testIsUtilityClass() {
        PrivateConstructorChecker
                .forClass(ProxerUtils.class)
                .expectedTypeOfException(UnsupportedOperationException.class)
                .check();
    }
}
