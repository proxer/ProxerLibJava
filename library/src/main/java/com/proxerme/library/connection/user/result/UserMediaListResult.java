package com.proxerme.library.connection.user.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.user.entitiy.UserMediaListEntry;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * Result of a {@link com.proxerme.library.connection.user.request.UserMediaListRequest}.
 *
 * @author Ruben Gees
 */

public class UserMediaListResult implements ProxerResult<UserMediaListEntry[]> {

    @Body(name = "data")
    UserMediaListEntry[] item;

    UserMediaListResult() {

    }

    /**
     * The constructor.
     *
     * @param item The array of entries.
     */
    public UserMediaListResult(@NonNull UserMediaListEntry[] item) {
        this.item = item;
    }

    /**
     * Returns the array of entries.
     *
     * @return The array of entries.
     */
    @NonNull
    @Override
    public UserMediaListEntry[] getItem() {
        return item;
    }
}
