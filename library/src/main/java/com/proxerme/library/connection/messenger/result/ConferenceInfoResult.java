package com.proxerme.library.connection.messenger.result;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.messenger.entity.ConferenceInfo;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * The class that represents the result of the {@link com.proxerme.library.connection.messenger.request.ConferenceInfoRequest}.
 *
 * @author Desnoo
 */
public class ConferenceInfoResult implements ProxerResult<ConferenceInfo> {

    @Body(name = "data")
    ConferenceInfo conferenceInfo;

    /**
     * Private constructor.
     */
    ConferenceInfoResult() {
    }

    /**
     * The constructor.
     *
     * @param conferenceInfo The result of the request.
     */
    public ConferenceInfoResult(ConferenceInfo conferenceInfo) {
        this.conferenceInfo = conferenceInfo;
    }

    @Override
    public ConferenceInfo getItem() {
        return this.conferenceInfo;
    }

}
