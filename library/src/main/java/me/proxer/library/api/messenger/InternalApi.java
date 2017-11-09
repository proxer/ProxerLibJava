package me.proxer.library.api.messenger;

import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.messenger.Conference;
import me.proxer.library.entity.messenger.ConferenceInfo;
import me.proxer.library.entity.messenger.Message;
import me.proxer.library.enums.ConferenceType;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import javax.annotation.ParametersAreNullableByDefault;
import java.util.List;

/**
 * @author Ruben Gees
 */
@ParametersAreNullableByDefault
interface InternalApi {

    @GET("messenger/conferences")
    ProxerCall<List<Conference>> conferences(@Query("p") Integer page,
                                             @Query("type") ConferenceType type);

    @GET("messenger/messages")
    ProxerCall<List<Message>> messages(@Query("conference_id") String conferenceId,
                                       @Query("message_id") String messageId,
                                       @Query("read") Boolean read);

    @GET("messenger/conferenceinfo")
    ProxerCall<ConferenceInfo> conferenceInfo(@Query("conference_id") String id);

    @FormUrlEncoded
    @POST("messenger/newconference")
    ProxerCall<String> createConference(@Field("text") String firstMessage,
                                        @Field("username") String username);

    @FormUrlEncoded
    @POST("messenger/newconferencegroup")
    ProxerCall<String> createConferenceGroup(@Field("topic") String topic,
                                             @Field("text") String firstMessage,
                                             @Field("users") List<String> participants);

    @FormUrlEncoded
    @POST("messenger/setmessage")
    ProxerCall<String> sendMessage(@Field("conference_id") String conferenceId,
                                   @Field("text") String message);

    @FormUrlEncoded
    @POST("messenger/setread")
    ProxerCall<Void> markConferenceAsRead(@Field("conference_id") String id);

    @FormUrlEncoded
    @POST("messenger/setunread")
    ProxerCall<Void> unmarkConferenceAsRead(@Field("conference_id") String id);

    @FormUrlEncoded
    @POST("messenger/setblock")
    ProxerCall<Void> markConferenceAsBlocked(@Field("conference_id") String id);

    @FormUrlEncoded
    @POST("messenger/setunblock")
    ProxerCall<Void> unmarkConferenceAsBlocked(@Field("conference_id") String id);

    @FormUrlEncoded
    @POST("messenger/setfavour")
    ProxerCall<Void> markConferenceAsFavorite(@Field("conference_id") String id);

    @FormUrlEncoded
    @POST("messenger/setunfavour")
    ProxerCall<Void> unmarkConferenceAsFavorite(@Field("conference_id") String id);
}
