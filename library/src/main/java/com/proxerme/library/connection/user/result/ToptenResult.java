package com.proxerme.library.connection.user.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.user.entitiy.ToptenEntry;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * The Result of a {@link com.proxerme.library.connection.user.request.ToptenRequest}.
 *
 * @author Ruben Gees
 */

public class ToptenResult implements ProxerResult<ToptenEntry[]> {

    @Body(name = "data")
    ToptenEntry[] item;

    /**
     * The constructor.
     *
     * @param item The array of entries.
     */
    public ToptenResult(@NonNull ToptenEntry[] item) {
        this.item = item;
    }

    ToptenResult() {

    }

    /**
     * Returns the array of entries.
     *
     * @return The array of entries.
     */
    @Override
    @NonNull
    public ToptenEntry[] getItem() {
        return item;
    }
}
