package me.proxer.library.api.media;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.media.CalendarEntry;

import java.util.List;

/**
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class CalendarEndpoint implements Endpoint<List<CalendarEntry>> {

    private final InternalApi internalApi;

    CalendarEndpoint(final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    @Override
    public ProxerCall<List<CalendarEntry>> build() {
        return internalApi.calendar();
    }
}
