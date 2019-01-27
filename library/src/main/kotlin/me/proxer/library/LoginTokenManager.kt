package me.proxer.library

/**
 * Interface for custom implementation of login token management.
 *
 * @author Ruben Gees
 */
interface LoginTokenManager {

    /**
     * Returns the token. If no token is available, null is returned.
     */
    fun provide(): String?

    /**
     * Persists the token. This could be in memory only, or on the hard disk in a file.
     */
    fun persist(loginToken: String?)
}
