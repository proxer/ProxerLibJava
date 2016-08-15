package com.proxerme.library.connection.messenger.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.messenger.entity.ConferenceInfoContainer;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * The class that represents the result of the
 * {@link com.proxerme.library.connection.messenger.request.ConferenceInfoRequest}.
 *
 * @author Desnoo
 */
public class ConferenceInfoResult implements ProxerResult<ConferenceInfoContainer> {

    @Body(name = "data")
    ConferenceInfoContainer conferenceInfoContainer;

    /**
     * Private constructor.
     */
    ConferenceInfoResult() {
    }

    /**
     * The constructor.
     *
     * @param conferenceInfoContainer The result of the request.
     */
    public ConferenceInfoResult(@NonNull ConferenceInfoContainer conferenceInfoContainer) {
        this.conferenceInfoContainer = conferenceInfoContainer;
    }

    @Override
    public ConferenceInfoContainer getItem() {
        return this.conferenceInfoContainer;
    }

}
