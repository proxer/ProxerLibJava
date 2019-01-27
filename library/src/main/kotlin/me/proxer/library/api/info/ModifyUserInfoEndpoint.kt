package me.proxer.library.api.info

import me.proxer.library.api.Endpoint
import me.proxer.library.api.ProxerCall

/**
 * Endpoint for adding an entry to the notes, favorites or finished entries.
 *
 * @author Ruben Gees
 */
class ModifyUserInfoEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String,
    private val type: UserInfoType
) : Endpoint<Unit> {

    override fun build(): ProxerCall<Unit> {
        return internalApi.modifyUserInfo(id, type)
    }
}
