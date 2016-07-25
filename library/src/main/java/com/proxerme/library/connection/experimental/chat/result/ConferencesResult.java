package com.proxerme.library.connection.experimental.chat.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.experimental.chat.entity.Conference;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * The result of a
 * {@link com.proxerme.library.connection.experimental.chat.request.ConferencesRequest}.
 *
 * @author Ruben Gees
 */
public class ConferencesResult implements ProxerResult<Conference[]> {

    @Body(name = "conferences")
    Conference[] conferences;

    ConferencesResult() {

    }

    /**
     * The constructor.
     *
     * @param conferences The array of conferences.
     */
    public ConferencesResult(@NonNull Conference[] conferences) {
        this.conferences = conferences;
    }

    /**
     * The array of retrieved conferences.
     *
     * @return The array of conferences.
     */
    @NonNull
    @Override
    public Conference[] getItem() {
        return conferences;
    }
}
