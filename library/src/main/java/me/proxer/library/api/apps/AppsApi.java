package me.proxer.library.api.apps;

import retrofit2.Retrofit;

/**
 * Api for the Media class.
 *
 * @author Ruben Gees
 */
public final class AppsApi {

    private final InternalApi internalApi;

    /**
     * Only for internal use.
     */
    public AppsApi(final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    public ErrorLogEndpoint errorLog(final String id, final String message) {
        return new ErrorLogEndpoint(internalApi, id, message);
    }
}
