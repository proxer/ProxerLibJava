package me.proxer.library.api.notifications;

import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.notifications.NewsArticle;
import me.proxer.library.entity.notifications.Notification;
import me.proxer.library.entity.notifications.NotificationInfo;
import me.proxer.library.enums.NotificationFilter;
import retrofit2.http.*;

import javax.annotation.ParametersAreNullableByDefault;
import java.util.List;

/**
 * @author Ruben Gees
 */
@ParametersAreNullableByDefault
interface InternalApi {

    @GET("notifications/news")
    ProxerCall<List<NewsArticle>> news(@Query("p") Integer page,
                                       @Query("limit") Integer limit,
                                       @Query("set_read") Boolean markAsRead);

    @GET("notifications/notifications")
    ProxerCall<List<Notification>> notifications(@Query("p") Integer page,
                                                 @Query("limit") Integer limit,
                                                 @Query("set_read") Boolean markAsRead,
                                                 @Query("filter") NotificationFilter filter);

    @GET("notifications/count")
    ProxerCall<NotificationInfo> notificationInfo();

    @FormUrlEncoded
    @POST("notifications/delete")
    ProxerCall<Void> deleteNotification(@Field("nid") String id);
}
