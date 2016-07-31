package com.proxerme.library.connection.list.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.list.entity.MediaListEntry;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * The result of a {@link com.proxerme.library.connection.list.request.MediaListRequest}.
 *
 * @author Ruben Gees
 */

public class MediaListResult implements ProxerResult<MediaListEntry[]> {

    @Body(name = "data")
    MediaListEntry[] item;

    MediaListResult() {
    }

    /**
     * The constructor.
     *
     * @param item The array of entries.
     */
    public MediaListResult(@NonNull MediaListEntry[] item) {
        this.item = item;
    }

    /**
     * Returns the array of entries.
     *
     * @return The array of entries.
     */
    @Override
    public MediaListEntry[] getItem() {
        return item;
    }
}
