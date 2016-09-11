package com.proxerme.library.connection.ucp.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.ucp.entitiy.Reminder;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public class ReminderResult extends ProxerResult<Reminder[]> {

    @Json(name = "data")
    private Reminder[] reminders;

    protected ReminderResult(Reminder[] reminders) {
        this.reminders = reminders;
    }

    @Override
    public Reminder[] getData() {
        return reminders;
    }
}
