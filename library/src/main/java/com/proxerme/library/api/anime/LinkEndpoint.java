package com.proxerme.library.api.anime;

import com.proxerme.library.api.Endpoint;
import com.proxerme.library.api.ProxerCall;
import lombok.experimental.Accessors;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;

/**
 * Endpoint for retrieving the link to the uploaded anime. This may be null, if the link is broken or has been deleted.
 * <p>
 * Note, that these links point to the web page and not the final data source.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class LinkEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String id;

    LinkEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<HttpUrl> build() {
        return internalApi.link(id);
    }
}
