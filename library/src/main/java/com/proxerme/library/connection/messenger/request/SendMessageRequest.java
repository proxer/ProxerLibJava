package com.proxerme.library.connection.messenger.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.messenger.MessengerRequest;
import com.proxerme.library.connection.messenger.result.SendMessageResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Collections;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Request for sending a single message. This API requires the user to be logged in.
 *
 * @author Ruben Gees
 */
public class SendMessageRequest extends MessengerRequest<String> {

    private static final String ENDPOINT = "setmessage";

    private static final String CONFERENCE_ID_PARAMETER = "conference_id";
    private static final String TEXT_PARAMETER = "text";

    private String conferenceId;
    private String text;

    /**
     * The constructor.
     *
     * @param conferenceId The conference to send the message to.
     * @param text         The contents of the message.
     */
    public SendMessageRequest(@NonNull String conferenceId, @NonNull String text) {
        this.conferenceId = conferenceId;
        this.text = text;
    }

    @Override
    protected ProxerResult<String> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(SendMessageResult.class).lenient().fromJson(body.source());
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        return ENDPOINT;
    }

    @Override
    protected String getMethod() {
        return POST;
    }

    @Nullable
    @Override
    protected RequestBody getRequestBody() {
        return new FormBody.Builder()
                .add(TEXT_PARAMETER, text)
                .build();
    }

    @NonNull
    @Override
    protected Iterable<Pair<String, ?>> getQueryParameters() {
        return Collections.<Pair<String, ?>>singletonList(
                new Pair<>(CONFERENCE_ID_PARAMETER, conferenceId)
        );
    }
}
