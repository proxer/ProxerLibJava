package me.proxer.library.api.ucp

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint

/**
 * Endpoint for deleting a comment. Does also delete the associated {link [me.proxer.library.entity.info.Entry]}
 * from the list of the user.
 *
 * @author Ruben Gees
 */
class DeleteCommentEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<Unit> {

    override fun build(): ProxerCall<Unit> {
        return internalApi.deleteComment(id)
    }
}
