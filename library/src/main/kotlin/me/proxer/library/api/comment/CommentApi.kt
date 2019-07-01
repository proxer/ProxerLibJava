package me.proxer.library.api.comment

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
    fun create(entryId: String): UpdateCommentEndpoint {
        return UpdateCommentEndpoint(internalApi, id = null, entryId = entryId)
    }

    /**
     * Returns the respective endpoint.
     */
    fun update(id: String): UpdateCommentEndpoint {
        return UpdateCommentEndpoint(internalApi, id = id, entryId = null)
    }
}
