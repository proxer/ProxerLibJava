package me.proxer.library.api.user

import me.proxer.library.api.Endpoint
import me.proxer.library.api.ProxerCall
import me.proxer.library.entity.user.User

/**
 * Endpoint for logging an user in.
 *
 * @author Ruben Gees
 */
class LoginEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val username: String,
    private val password: String
) : Endpoint<User> {

    private var secretKey: String? = null

    /**
     * Sets the secret key for authentication with 2FA.
     */
    fun secretKey(secretKey: String?) = this.apply { this.secretKey = secretKey }

    override fun build(): ProxerCall<User> {
        return internalApi.login(username, password, secretKey)
    }
}
