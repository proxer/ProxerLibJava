package me.proxer.library.entitiy.notifications;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerDateItem;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.enums.NotificationType;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

/**
 * @author Ruben Gees
 */
@Value
public class Notification implements ProxerIdItem, ProxerDateItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the type of the notification.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "type")
    private NotificationType type;

    /**
     * Returns the id of the content. This depends on the {@link #type}.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "tid")
    private String contentId;

    /**
     * Returns the link to the content.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "link")
    private HttpUrl contentLink;

    /**
     * Returns the description of the content. This is directly usable for presentation.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "linktext")
    private String text;

    /**
     * Returns the time.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "time")
    private Date date;

    /**
     * Returns additional info, depending on the {@link #type}.
     */
    @Getter(onMethod = @__({@Nullable}))
    @Json(name = "description")
    private String additionalDescription;
}
