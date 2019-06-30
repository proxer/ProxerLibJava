package me.proxer.library.api.comment

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.enums.UserMediaProgress
import me.proxer.library.util.ProxerUtils

/**
 * Endpoint for updating a comment.
 *
 * @author Ruben Gees
 */
class UpdateCommentEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<Unit> {

    private var rating: Int? = null
    private var episode: Int? = null
    private var mediaProgress: UserMediaProgress? = null
    private var comment: String? = null

    /**
     * Sets the rating for the comment. Must be between 0 and 10, while 0 means no rating is given by the user.
     */
    fun rating(rating: Int?): UpdateCommentEndpoint {
        require(rating in 0..10) {
            "The rating must be between 0 and 10."
        }

        return this.apply { this.rating = rating }
    }

    /**
     * Sets the episode the user is currently at.
     */
    fun episode(episode: Int?): UpdateCommentEndpoint {
        return this.apply { this.episode = episode }
    }

    /**
     * Sets the overall progress of the user.
     */
    fun progress(mediaProgress: UserMediaProgress?): UpdateCommentEndpoint {
        return this.apply { this.mediaProgress = mediaProgress }
    }

    /**
     * Sets the content of the comment. May contain BBCode tags and can be empty.
     */
    fun comment(comment: String): UpdateCommentEndpoint {
        return this.apply { this.comment = comment }
    }

    override fun build(): ProxerCall<Unit> {
        return internalApi.update(
            id, rating, episode, mediaProgress?.let { ProxerUtils.getSafeApiEnumName(it).toInt() }, comment
        )
    }
}
