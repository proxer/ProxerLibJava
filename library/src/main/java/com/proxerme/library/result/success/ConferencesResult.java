package com.proxerme.library.result.success;

import android.support.annotation.NonNull;

import com.proxerme.library.entity.Conference;
import com.proxerme.library.result.ProxerListResult;

import java.util.List;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class ConferencesResult implements ProxerListResult<Conference> {

    private List<Conference> conferences;

    public ConferencesResult(@NonNull List<Conference> conferences) {
        this.conferences = conferences;
    }

    @NonNull
    @Override
    public List<Conference> getItem() {
        return conferences;
    }
}
