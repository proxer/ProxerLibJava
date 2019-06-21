package me.proxer.library.api.user

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.user.UserAbout

/**
 * Endpoint for requesting all information of an user.
 *
 * @author Ruben Gees
 */
class UserAboutEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val userId: String?,
    private val username: String?
) : Endpoint<UserAbout> {

    init {
        require(userId.isNullOrBlank().not() || username.isNullOrBlank().not()) {
            "You must pass either an userId or an username."
        }
    }

    override fun build(): ProxerCall<UserAbout> {
        return internalApi.userAbout(userId, username)
    }
}
