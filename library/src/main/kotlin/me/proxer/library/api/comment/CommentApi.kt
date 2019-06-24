package me.proxer.library.api.comment

import me.proxer.library.enums.UserMediaProgress
import retrofit2.Retrofit

/**
 * Api for the Comment class.
 *
 * @author Ruben Gees
 */
class CommentApi internal constructor(retrofit: Retrofit) {

    private val internalApi: InternalApi = retrofit.create(InternalApi::class.java)

    /**
     * Returns the respective endpoint.
     */
    fun comment(id: String? = null, entryId: String? = null): CommentEndpoint {
        return CommentEndpoint(internalApi, id, entryId)
    }

    /**
     * Returns the respective endpoint.
     */
    fun create(entryId: String, mediaProgress: UserMediaProgress): CreateCommentEndpoint {
        return CreateCommentEndpoint(internalApi, entryId, mediaProgress)
    }
}
