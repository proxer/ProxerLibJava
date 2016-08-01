package com.proxerme.library.connection.list.result;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.list.entity.MediaListEntry;

/**
 * Result of a {@link com.proxerme.library.connection.list.request.MediaSearchRequest}.
 *
 * @author Ruben Gees
 */

public class MediaSearchResult extends MediaListResult {

    MediaSearchResult() {
    }

    /**
     * The constructor.
     *
     * @param item The array of entries.
     */
    public MediaSearchResult(@NonNull MediaListEntry[] item) {
        super(item);
    }
}
