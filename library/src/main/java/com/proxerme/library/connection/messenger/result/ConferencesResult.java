package com.proxerme.library.connection.messenger.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.messenger.entity.Conference;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * Result of a {@link com.proxerme.library.connection.messenger.request.ConferencesRequest}.
 *
 * @author Ruben Gees
 */

public class ConferencesResult implements ProxerResult<Conference[]> {

    @Body(name = "data")
    Conference[] item;

    ConferencesResult() {
    }

    /**
     * The constructor.
     *
     * @param item The array of received conferences.
     */
    public ConferencesResult(@NonNull Conference[] item) {
        this.item = item;
    }

    /**
     * Returns the array of retrieved conferences.
     * @return The array of conferences.
     */
    @Override
    public Conference[] getItem() {
        return item;
    }
}
