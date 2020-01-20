package me.proxer.library.internal

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class DefaultLoginTokenManagerTest {

    private val loginTokenManager = DefaultLoginTokenManager()

    @Test
    fun testProvideBeforePersist() {
        loginTokenManager.provide().shouldBeNull()
    }

    @Test
    fun testProvideAfterPersist() {
        loginTokenManager.persist("test")

        loginTokenManager.provide() shouldBeEqualTo "test"
    }

    @Test
    fun testPersistNull() {
        loginTokenManager.persist("test")
        loginTokenManager.persist(null)

        loginTokenManager.provide().shouldBeNull()
    }
}
