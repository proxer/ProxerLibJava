package com.proxerme.library.connection.messenger.request;

import android.support.annotation.NonNull;

import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.messenger.result.ConstantsResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * @author Desnoo
 */
public class ConstantsRequest extends ProxerRequest<ConstantsResult> {

    private static final String CONFERENCE_CONSTANTS_URL = "/api/v1/messenger/constants";


    @Override
    protected int getTag() {
        return ProxerTag.MESSENGER_CONFERENCE_CONSTANTS;
    }

    @Override
    protected ConstantsResult parse(@NonNull Response response) throws Exception {
        return response.asClass(ConstantsResult.class);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + CONFERENCE_CONSTANTS_URL;
    }
}
