package com.proxerme.library.connection.messenger.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.messenger.entity.Favour;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * The result of the {@link com.proxerme.library.connection.messenger.request.SetFavourRequest} or GetFavour...
 *
 * @author Desnoo
 */
public class FavourResult implements ProxerResult<Favour> {

    @Body(name = "message")
    Favour favour;

    FavourResult() {
    }

    /**
     * The Constructor.
     *
     * @param favour The favour response message.
     */
    public FavourResult(@NonNull Favour favour) {
        this.favour = favour;
    }

    @NonNull
    @Override
    public Favour getItem() {
        return favour;
    }
}
