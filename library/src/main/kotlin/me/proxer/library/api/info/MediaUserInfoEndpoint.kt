package me.proxer.library.api.info

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.info.MediaUserInfo
import me.proxer.library.entity.info.TranslatorGroup

/**
 * Endpoint for retrieving all information of an [TranslatorGroup].
 *
 * @author Ruben Gees
 */
class MediaUserInfoEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<MediaUserInfo> {

    override fun build(): ProxerCall<MediaUserInfo> {
        return internalApi.userInfo(id)
    }
}
