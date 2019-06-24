package me.proxer.library.api.comment

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.enums.UserMediaProgress
import me.proxer.library.util.ProxerUtils

/**
 * Endpoint for creating a comment associated with a specific [me.proxer.library.entity.info.Entry].
 *
 * Comments are also used for persisting the current state of the user related to the entry. Thus, at least the progress
 * is required for creating a new comment. Most of the time, the update method should be called, because (empty)
 * comments get created automatically when the user sets a bookmark.
 */
class CreateCommentEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val entryId: String,
    private val mediaProgress: UserMediaProgress
) : Endpoint<Unit> {

    private var rating: Int? = null
    private var episode: Int? = null
    private var comment: String? = null

    /**
     * Sets the rating for the comment. Must be between 0 and 10, while 0 means no rating is given by the user.
     */
    fun rating(rating: Int): CreateCommentEndpoint {
        require(rating in 0..10) {
            "The rating must be between 0 and 10."
        }

        return this.apply { this.rating = rating }
    }

    /**
     * Sets the episode the user is currently at.
     */
    fun episode(episode: Int): CreateCommentEndpoint {
        return this.apply { this.episode = episode }
    }

    /**
     * Sets the content of the comment. May contain BBCode tags and can be empty.
     */
    fun comment(comment: String): CreateCommentEndpoint {
        return this.apply { this.comment = comment }
    }

    override fun build(): ProxerCall<Unit> {
        return internalApi.create(
            entryId,
            rating ?: 0,
            episode ?: 0,
            ProxerUtils.getSafeApiEnumName(mediaProgress).toInt(),
            comment ?: ""
        )
    }
}
