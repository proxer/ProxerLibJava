package com.proxerme.library.connection.info.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.info.entity.EntryCore;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * Holds the result of the {@link com.proxerme.library.connection.info.request.EntryCoreRequest}.
 *
 * @author Desnoo
 */
public class EntryCoreResult implements ProxerResult<EntryCore> {

    @Body(name = "data")
    EntryCore item;

    /**
     * Private constructor.
     */
    EntryCoreResult() {
    }

    /**
     * The copy constructor.
     *
     * @param item the EntryCore object to assign.
     */
    public EntryCoreResult(@NonNull EntryCore item) {
        this.item = item;
    }

    /**
     * Gets the object of the request.
     *
     * @return the requested object.
     */
    @Override
    public EntryCore getItem() {
        return item;
    }
}
