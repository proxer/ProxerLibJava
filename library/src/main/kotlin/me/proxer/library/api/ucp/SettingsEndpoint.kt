package me.proxer.library.api.ucp

import me.proxer.library.api.Endpoint
import me.proxer.library.api.ProxerCall
import me.proxer.library.entity.ucp.UcpSettings

/**
 * Endpoint for retrieving the settings of the user.
 *
 * @author Ruben Gees
 */
class SettingsEndpoint internal constructor(private val internalApi: InternalApi) : Endpoint<UcpSettings> {

    override fun build(): ProxerCall<UcpSettings> {
        return internalApi.settings()
    }
}
