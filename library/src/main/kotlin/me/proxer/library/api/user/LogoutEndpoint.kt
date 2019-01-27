package me.proxer.library.api.user

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint

/**
 * Endpoint for logging out an user.
 *
 * @author Ruben Gees
 */
class LogoutEndpoint internal constructor(private val internalApi: InternalApi) : Endpoint<Unit> {

    override fun build(): ProxerCall<Unit> {
        return internalApi.logout()
    }
}
