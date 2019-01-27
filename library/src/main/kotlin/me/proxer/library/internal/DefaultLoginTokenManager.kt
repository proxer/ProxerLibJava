package me.proxer.library.internal

import me.proxer.library.LoginTokenManager

/**
 * @author Ruben Gees
 */
internal class DefaultLoginTokenManager : LoginTokenManager {

    @Volatile
    private var currentToken: String? = null

    override fun provide(): String? {
        return currentToken
    }

    override fun persist(loginToken: String?) {
        currentToken = loginToken
    }
}
