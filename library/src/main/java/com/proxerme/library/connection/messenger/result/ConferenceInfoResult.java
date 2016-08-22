package com.proxerme.library.connection.messenger.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.messenger.entity.ConferenceInfoContainer;
import com.squareup.moshi.Json;

public class ConferenceInfoResult extends ProxerResult<ConferenceInfoContainer> {

    @Json(name = "data")
    private ConferenceInfoContainer conferenceInfoContainer;

    private ConferenceInfoResult() {
    }

    @Override
    public ConferenceInfoContainer getData() {
        return conferenceInfoContainer;
    }
}
