package me.proxer.library.api.comment

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.info.Comment

/**
 * Endpoint for sending error logs.
 *
 * @author Ruben Gees
 */
class CommentEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String?,
    private val entryId: String?
) : Endpoint<Comment> {

    init {
        require(id.isNullOrBlank().not() || entryId.isNullOrBlank().not()) {
            "You must pass either an id or an entryId."
        }
    }

    override fun build(): ProxerCall<Comment> {
        return when {
            id != null -> internalApi.commentById(id)
            entryId != null -> internalApi.commentByEntry(entryId)
            else -> error("id and entryId are null")
        }
    }
}
