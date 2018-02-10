package me.proxer.library.api.media;

import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.media.CalendarEntry;
import retrofit2.http.GET;

import javax.annotation.ParametersAreNullableByDefault;
import java.util.List;

/**
 * @author Ruben Gees
 */
@ParametersAreNullableByDefault
public interface InternalApi {

    @GET("media/calendar")
    ProxerCall<List<CalendarEntry>> calendar();
}
