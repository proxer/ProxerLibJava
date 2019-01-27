package me.proxer.library.api

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class DefaultLoginTokenManagerTest {

    private val loginTokenManager = DefaultLoginTokenManager()

    @Test
    fun testProvideBeforePersist() {
        assertThat(loginTokenManager.provide()).isNull()
    }

    @Test
    fun testProvideAfterPersist() {
        loginTokenManager.persist("test")

        assertThat(loginTokenManager.provide()).isEqualTo("test")
    }

    @Test
    fun testPersistNull() {
        loginTokenManager.persist("test")
        loginTokenManager.persist(null)

        assertThat(loginTokenManager.provide()).isNull()
    }
}
