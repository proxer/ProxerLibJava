package com.proxerme.library.event.success;

import android.support.annotation.NonNull;

import com.proxerme.library.entity.Conference;
import com.proxerme.library.event.IEvent;

import java.util.List;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class ConferencesEvent implements IEvent {

    private List<Conference> conferences;

    public ConferencesEvent(@NonNull List<Conference> conferences) {
        this.conferences = conferences;
    }

    @NonNull
    @Override
    public Object getItem() {
        return conferences;
    }
}
