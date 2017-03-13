package com.proxerme.library.util;

import com.proxerme.library.enums.Genre;
import com.pushtorefresh.private_constructor_checker.PrivateConstructorChecker;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class UtilsTest {

    @Test
    public void testGetEnumName() throws NoSuchFieldException {
        assertThat(Utils.getEnumName(Genre.COMEDY)).isEqualTo("Comedy");
    }

    @Test
    public void testJoin() {
        assertThat(Utils.join(";", Arrays.asList("a", "b", "c"))).isEqualTo("a;b;c");
    }

    @Test
    public void testJoinEmpty() {
        assertThat(Utils.join(";", Collections.emptyList())).isEmpty();
    }

    @Test
    public void testIsUtilityClass() {
        PrivateConstructorChecker
                .forClass(Utils.class)
                .expectedTypeOfException(UnsupportedOperationException.class)
                .check();
    }
}
