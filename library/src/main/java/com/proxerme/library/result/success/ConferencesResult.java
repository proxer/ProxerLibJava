package com.proxerme.library.result.success;

import android.support.annotation.NonNull;

import com.proxerme.library.entity.Conference;
import com.proxerme.library.result.ProxerResult;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class ConferencesResult implements ProxerResult<Conference[]> {

    private Conference[] conferences;

    public ConferencesResult(@NonNull Conference[] conferences) {
        this.conferences = conferences;
    }

    @NonNull
    @Override
    public Conference[] getItem() {
        return conferences;
    }
}
