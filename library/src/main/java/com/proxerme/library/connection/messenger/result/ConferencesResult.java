package com.proxerme.library.connection.messenger.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.messenger.entity.Conference;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public class ConferencesResult implements ProxerResult<Conference[]> {

    @Body(name = "data")
    Conference[] item;

    ConferencesResult() {
    }

    public ConferencesResult(@NonNull Conference[] item) {
        this.item = item;
    }

    @Override
    public Conference[] getItem() {
        return item;
    }
}
