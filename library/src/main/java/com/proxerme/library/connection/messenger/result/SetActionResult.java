package com.proxerme.library.connection.messenger.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.messenger.entity.SetAction;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * The result of {@link com.proxerme.library.connection.messenger.request.SetFavourRequest},
 * {@link com.proxerme.library.connection.messenger.request.SetUnfavourRequest},
 * {@link com.proxerme.library.connection.messenger.request.SetBlockRequest},
 * {@link com.proxerme.library.connection.messenger.request.SetUnblockRequest} or
 * {@link com.proxerme.library.connection.messenger.request.SetUnreadRequest}.
 *
 * @author Desnoo
 */
public class SetActionResult implements ProxerResult<SetAction> {

    @Body(name = "message")
    SetAction setAction;

    SetActionResult() {
    }

    /**
     * The Constructor.
     *
     * @param setAction The favour response message.
     */
    public SetActionResult(@NonNull SetAction setAction) {
        this.setAction = setAction;
    }

    @NonNull
    @Override
    public SetAction getItem() {
        return setAction;
    }
}
