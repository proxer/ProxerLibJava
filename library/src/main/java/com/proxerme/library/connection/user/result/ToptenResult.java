package com.proxerme.library.connection.user.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.user.entitiy.ToptenEntry;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public class ToptenResult implements ProxerResult<ToptenEntry[]> {

    @Body(name = "body")
    ToptenEntry[] item;

    public ToptenResult(@NonNull ToptenEntry[] item) {
        this.item = item;
    }

    ToptenResult() {

    }

    @Override
    @NonNull
    public ToptenEntry[] getItem() {
        return item;
    }
}
