package me.proxer.library.entity.notifications;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerDateItem;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.enums.NotificationType;
import okhttp3.HttpUrl;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * @author Ruben Gees
 */
@Value
public class Notification implements ProxerIdItem, ProxerDateItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the type of the notification.
     */
    @Json(name = "type")
    private NotificationType type;

    /**
     * Returns the id of the content. This depends on the {@link #type}.
     */
    @Json(name = "tid")
    private String contentId;

    /**
     * Returns the link to the content.
     */
    @Json(name = "link")
    private HttpUrl contentLink;

    /**
     * Returns the description of the content. This is directly usable for presentation.
     */
    @Json(name = "linktext")
    private String text;

    /**
     * Returns the time.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "time")
    private Date date;

    /**
     * Returns additional info, depending on the {@link #type}.
     */
    @Getter(onMethod = @__({@Nullable}))
    @Json(name = "description")
    private String additionalDescription;
}
