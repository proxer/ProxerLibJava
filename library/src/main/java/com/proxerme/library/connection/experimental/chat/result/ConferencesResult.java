package com.proxerme.library.connection.experimental.chat.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.experimental.chat.entity.Conference;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class ConferencesResult implements ProxerResult<Conference[]> {

    @Body(name = "conferences")
    Conference[] conferences;

    ConferencesResult() {

    }

    public ConferencesResult(@NonNull Conference[] conferences) {
        this.conferences = conferences;
    }

    @NonNull
    @Override
    public Conference[] getItem() {
        return conferences;
    }
}
