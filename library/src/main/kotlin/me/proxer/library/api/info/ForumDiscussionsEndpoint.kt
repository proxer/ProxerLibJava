package me.proxer.library.api.info

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.info.ForumDiscussion

/**
 * Endpoint for retrieving the list of forum discussions associated with an [me.proxer.library.entity.info.Entry].
 *
 * @author Ruben Gees
 */
class ForumDiscussionsEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<List<ForumDiscussion>> {

    override fun build(): ProxerCall<List<ForumDiscussion>> {
        return internalApi.forumDiscussions(id)
    }
}
