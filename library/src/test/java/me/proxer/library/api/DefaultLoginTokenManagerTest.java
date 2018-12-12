package me.proxer.library.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * @author Ruben Gees
 */
class DefaultLoginTokenManagerTest {

    private DefaultLoginTokenManager loginTokenManager;

    @BeforeEach
    void setUp() {
        loginTokenManager = new DefaultLoginTokenManager();
    }

    @Test
    void testProvideBeforePersist() {
        assertThat(loginTokenManager.provide()).isNull();
    }

    @Test
    void testProvideAfterPersist() {
        loginTokenManager.persist("test");

        assertThat(loginTokenManager.provide()).isEqualTo("test");
    }

    @Test
    void testPersistNull() {
        loginTokenManager.persist("test");
        loginTokenManager.persist(null);

        assertThat(loginTokenManager.provide()).isNull();
    }
}
