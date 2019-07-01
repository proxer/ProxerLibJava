package me.proxer.library.api.comment

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.enums.UserMediaProgress
import me.proxer.library.util.ProxerUtils

/**
 * Endpoint for updating or creating a comment.
 *
 * If the id is passed an update will performed (requiring that a comment with that id exists).
 * If the entryId is passed a new comment for the associated [me.proxer.library.entity.info.Entry] will be
 * created (requiring that no comment exists yet).
 *
 * Comments are also used for persisting the current state of the user related to the entry. Thus, at least the progress
 * is required for creating a new comment. Most of the time, the update method should be called, because (empty)
 * comments get created automatically when the user sets a bookmark.
 *
 * This Endpoint is used for both the /create and /update apis.
 *
 * @author Ruben Gees
 */
class UpdateCommentEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String?,
    private val entryId: String?
) : Endpoint<Unit> {

    private var rating: Int? = null
    private var episode: Int? = null
    private var mediaProgress: UserMediaProgress? = null
    private var comment: String? = null

    init {
        require(id.isNullOrBlank().not() || entryId.isNullOrBlank().not()) {
            "You must pass either an id or an entryId."
        }
    }

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
        val apiMediaProgress = mediaProgress?.let { ProxerUtils.getSafeApiEnumName(it).toInt() }

        return when {
            id != null -> internalApi.update(id, rating, episode, apiMediaProgress, comment)
            entryId != null -> internalApi.create(entryId, rating, episode, apiMediaProgress, comment)
            else -> error("id and entryId are null")
        }
    }
}
