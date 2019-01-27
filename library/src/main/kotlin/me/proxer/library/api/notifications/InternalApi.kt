package me.proxer.library.api.notifications

import me.proxer.library.ProxerCall
import me.proxer.library.entity.notifications.NewsArticle
import me.proxer.library.entity.notifications.Notification
import me.proxer.library.entity.notifications.NotificationInfo
import me.proxer.library.enums.NotificationFilter
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author Ruben Gees
 */
@Suppress("UndocumentedPublicFunction")
internal interface InternalApi {

    @GET("notifications/news")
    fun news(
        @Query("p") page: Int?,
        @Query("limit") limit: Int?,
        @Query("set_read") markAsRead: Boolean?
    ): ProxerCall<List<NewsArticle>>

    @GET("notifications/notifications")
    fun notifications(
        @Query("p") page: Int?,
        @Query("limit") limit: Int?,
        @Query("set_read") markAsRead: Boolean?,
        @Query("filter") filter: NotificationFilter?
    ): ProxerCall<List<Notification>>

    @GET("notifications/count")
    fun notificationInfo(): ProxerCall<NotificationInfo>

    @FormUrlEncoded
    @POST("notifications/delete")
    fun deleteNotification(@Field("nid") id: String?): ProxerCall<Unit>
}
