package me.proxer.library.api.forum;

import retrofit2.Retrofit;

/**
 * API for the Forum class.
 *
 * @author Ruben Gees
 */
public class ForumApi {

    private final InternalApi internalApi;

    /**
     * Only for internal use.
     */
    public ForumApi(final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    public TopicEndpoint topic(final String id) {
        return new TopicEndpoint(internalApi, id);
    }
}
