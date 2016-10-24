package com.proxerme.library.connection.messenger.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.messenger.MessengerRequest;
import com.proxerme.library.connection.messenger.result.NewConferenceResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Collection;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Request to create a new Conference. This can be either a group chat or a chat with a single
 * user. (Use the {@link #NewConferenceRequest(String)} constructor for a single user and
 * either the {@link #NewConferenceRequest(String, String...)} or
 * {@link #NewConferenceRequest(String, Collection)} constructor for a group)
 * The user must be logged in to use this API.
 *
 * @author Ruben Gees
 */
public class NewConferenceRequest extends MessengerRequest<String> {

    private static final String ENDPOINT = "newconference";
    private static final String ENDPOINT_GROUP = "newconferencegroup";

    private static final String TOPIC_PARAMETER = "topic";
    private static final String USERS_PARAMETER = "users[]";
    private static final String USERNAME_PARAMETER = "username";
    private static final String TEXT_PARAMETER = "text";

    private String topic;
    private String[] users;
    private String user;

    @Nullable
    private String firstMessage;

    /**
     * The constructor. If this one is used, a non-group chat will be created.
     *
     * @param user The user.
     */
    public NewConferenceRequest(@NonNull String user) {
        this.user = user;
    }

    /**
     * The constructor. If this one is used, a group chat will be created.
     *
     * @param topic The topic of the new group chat.
     * @param users The participants.
     */
    public NewConferenceRequest(@NonNull String topic, @NonNull @Size(min = 1) String... users) {
        this.topic = topic;
        this.users = users;
    }

    /**
     * The constructor. If this one is used, a group chat will be created.
     *
     * @param topic The topic of the new group chat.
     * @param users The participants.
     */
    public NewConferenceRequest(@NonNull String topic,
                                @NonNull @Size(min = 1) Collection<String> users) {
        this.topic = topic;
        this.users = users.toArray(new String[users.size()]);
    }

    /**
     * Sets the optional first message.
     *
     * @param message The message.
     * @return This request.
     */
    public NewConferenceRequest withFirstMessage(@NonNull String message) {
        this.firstMessage = message;

        return this;
    }

    @Override
    protected ProxerResult<String> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(NewConferenceResult.class).fromJson(body.source());
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        if (users != null) {
            return ENDPOINT_GROUP;
        } else {
            return ENDPOINT;
        }
    }

    @Override
    protected String getMethod() {
        return POST;
    }

    @Nullable
    @Override
    protected RequestBody getRequestBody() {
        FormBody.Builder builder = new FormBody.Builder();

        if (users != null) {
            builder.add(TOPIC_PARAMETER, topic);

            for (String user : users) {
                builder.add(USERS_PARAMETER, user);
            }
        } else {
            builder.add(USERNAME_PARAMETER, user);
        }

        if (firstMessage != null) {
            builder.add(TEXT_PARAMETER, firstMessage);
        }

        return builder.build();
    }
}
