package me.proxer.library.api.media;

import retrofit2.Retrofit;

/**
 * Api for the Media class.
 *
 * @author Ruben Gees
 */
public final class MediaApi {

    private final InternalApi internalApi;

    /**
     * Only for internal use.
     */
    public MediaApi(final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    public CalendarEndpoint calendar() {
        return new CalendarEndpoint(internalApi);
    }
}
