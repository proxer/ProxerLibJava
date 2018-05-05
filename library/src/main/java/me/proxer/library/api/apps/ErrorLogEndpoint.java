package me.proxer.library.api.apps;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;

import javax.annotation.Nullable;

/**
 * Endpoint for sending error logs.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class ErrorLogEndpoint implements Endpoint<Void> {

    private final InternalApi internalApi;

    private final String id;
    private final String message;

    /**
     * Sets if the error log should be sent anonymously. If not set, this behaves as true.
     */
    @Nullable
    @Setter
    private Boolean anonym;

    /**
     * Sets if the developer should get a notification. If not set, this behaves as true.
     */
    @Nullable
    @Setter
    private Boolean silent;

    ErrorLogEndpoint(final InternalApi internalApi, final String id, final String message) {
        this.internalApi = internalApi;
        this.id = id;
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<Void> build() {
        return internalApi.errorLog(id, message, anonym, silent);
    }
}
