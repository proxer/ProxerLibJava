package com.proxerme.library.connection.messenger.result;

import android.support.annotation.Nullable;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public class SendMessageResult implements ProxerResult<String> {

    @Nullable
    @Body(name = "data")
    String item;

    SendMessageResult() {
    }

    public SendMessageResult(@Nullable String item) {
        this.item = item;
    }

    @Override
    @Nullable
    public String getItem() {
        return item;
    }
}
