package me.proxer.library.api.apps

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint

/**
 * Endpoint for sending error logs.
 *
 * @author Ruben Gees
 */
class ErrorLogEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String,
    private val message: String
) : Endpoint<Unit?> {

    private var anonym: Boolean? = null
    private var silent: Boolean? = null

    /**
     * Sets if the error log should be sent anonymously. If not set, this behaves as true.
     */
    fun anonym(anonym: Boolean? = true) = this.apply { this.anonym = anonym }

    /**
     * Sets if the developer should get a notification. If not set, this behaves as true.
     */
    fun silent(silent: Boolean? = true) = this.apply { this.silent = silent }

    override fun build(): ProxerCall<Unit?> {
        return internalApi.errorLog(id, message, anonym, silent)
    }
}
