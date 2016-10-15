package com.proxerme.library.connection.messenger.request;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.messenger.MessengerRequest;
import com.proxerme.library.connection.messenger.result.ModifyConferenceResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collections;

import okhttp3.ResponseBody;

/**
 * Request for modification of a
 * {@link com.proxerme.library.connection.messenger.entity.Conference}. Please take a look at the
 * public constants for the available modifications.
 * This API requires the user to be logged in.
 *
 * @author Ruben Gees
 */
public class ModifyConferenceRequest extends MessengerRequest<Void> {

    /**
     * Sets a conference as unread.
     */
    public static final String UNREAD = "setunread";

    /**
     * Blocks a conference.
     */
    public static final String BLOCK = "setblock";

    /**
     * Unblocks a conference.
     */
    public static final String UNBLOCK = "setunblock";

    /**
     * Marks a conference as favorite.
     */
    public static final String FAVOUR = "setfavour";

    /**
     * Unmarks a conference as favorite.
     */
    public static final String UNFAVOUR = "setunfavour";

    private static final String CONFERENCE_ID_PARAMETER = "conference_id";

    private String conferenceId;
    private String modification;

    /**
     * The constructor.
     *
     * @param conferenceId The id of the
     *                     {@link com.proxerme.library.connection.messenger.entity.Conference} to
     *                     modify.
     * @param modification The modification to make.
     */
    public ModifyConferenceRequest(@NonNull String conferenceId,
                                   @NonNull @Modification String modification) {
        this.conferenceId = conferenceId;
        this.modification = modification;
    }

    @Override
    protected ProxerResult<Void> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(ModifyConferenceResult.class).lenient().fromJson(body.source());
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        return modification;
    }

    @NonNull
    @Override
    protected Iterable<Pair<String, ?>> getQueryParameters() {
        return Collections.<Pair<String, ?>>singletonList(
                new Pair<>(CONFERENCE_ID_PARAMETER, conferenceId)
        );
    }

    @StringDef({UNREAD, BLOCK, UNBLOCK, FAVOUR, UNFAVOUR})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface Modification {
    }
}
