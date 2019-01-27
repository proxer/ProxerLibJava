package me.proxer.library.api.user

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.user.UserInfo

/**
 * Endpoint for requesting various information of an user.
 *
 * @author Ruben Gees
 */
class UserInfoEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val userId: String?,
    private val username: String?
) : Endpoint<UserInfo> {

    init {
        if (userId.isNullOrBlank() && username.isNullOrBlank()) {
            throw IllegalArgumentException("You must pass either an userId or an username.")
        }
    }

    override fun build(): ProxerCall<UserInfo> {
        return internalApi.userInfo(userId, username)
    }
}
