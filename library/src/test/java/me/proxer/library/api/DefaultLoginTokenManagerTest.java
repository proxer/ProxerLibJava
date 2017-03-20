package me.proxer.library.api;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * @author Ruben Gees
 */
public class DefaultLoginTokenManagerTest {

    private DefaultLoginTokenManager loginTokenManager;

    @Before
    public void setUp() {
        loginTokenManager = new DefaultLoginTokenManager();
    }

    @Test
    public void testProvideBeforePersist() {
        assertThat(loginTokenManager.provide()).isNull();
    }

    @Test
    public void testProvideAfterPersist() {
        loginTokenManager.persist("test");

        assertThat(loginTokenManager.provide()).isEqualTo("test");
    }

    @Test
    public void testPersistNull() {
        loginTokenManager.persist("test");
        loginTokenManager.persist(null);

        assertThat(loginTokenManager.provide()).isNull();
    }
}
