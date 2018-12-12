package me.proxer.library.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class BooleanAdapterFactoryTest {

    private BooleanAdapterFactory factory;
    private Moshi moshi;

    private Annotation numberBasedBooleanAnnotation;

    @BeforeEach
    void setUp() {
        factory = new BooleanAdapterFactory();
        moshi = new Moshi.Builder().add(factory).build();

        numberBasedBooleanAnnotation = Dummy.class.getAnnotation(NumberBasedBoolean.class);
    }

    @Test
    void testCreatePrimitive() {
        final JsonAdapter<?> defaultBooleanAdapter = new Moshi.Builder().build().adapter(boolean.class);

        assertThat(factory.create(boolean.class, Collections.emptySet(), moshi))
                .isNotNull()
                .isInstanceOf(defaultBooleanAdapter.getClass());
    }

    @Test
    void testCreateBoxed() {
        final JsonAdapter<?> defaultBooleanAdapter = new Moshi.Builder().build().adapter(Boolean.class);

        assertThat(factory.create(Boolean.class, Collections.emptySet(), moshi))
                .isNotNull()
                .isInstanceOf(defaultBooleanAdapter.getClass());
    }

    @Test
    void testCreatePrimitiveNumberBased() {
        final JsonAdapter<?> defaultBooleanAdapter = new Moshi.Builder().build().adapter(boolean.class);

        assertThat(factory.create(boolean.class, Collections.singleton(numberBasedBooleanAnnotation), moshi))
                .isNotNull()
                .isNotInstanceOf(defaultBooleanAdapter.getClass());
    }

    @Test
    void testCreateBoxedNumberBased() {
        final JsonAdapter<?> defaultBooleanAdapter = new Moshi.Builder().build().adapter(Boolean.class);

        assertThat(factory.create(Boolean.class, Collections.singleton(numberBasedBooleanAnnotation), moshi))
                .isNotNull()
                .isNotInstanceOf(defaultBooleanAdapter.getClass());
    }

    @Test
    void testCreateNonBoolean() {
        assertThat(factory.create(String.class, new HashSet<>(), moshi)).isNull();
    }

    @NumberBasedBoolean
    private static class Dummy {
    }
}
