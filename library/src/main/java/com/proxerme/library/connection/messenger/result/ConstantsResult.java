package com.proxerme.library.connection.messenger.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.messenger.entity.Constants;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * The class that represents the result of the
 * {@link com.proxerme.library.connection.messenger.request.ConstantsRequest}.
 *
 * @author Desnoo
 */
public class ConstantsResult implements ProxerResult<Constants> {

    @Body(name = "data")
    Constants constants;

    /**
     * Private Constructor.
     */
    ConstantsResult() {
    }

    /**
     * The constructor.
     *
     * @param constants The constants retrieved by the request.
     **/
    public ConstantsResult(@NonNull Constants constants) {
        this.constants = constants;
    }

    @Override
    public Constants getItem() {
        return this.constants;
    }
}
