package com.proxerme.library.connection.messenger.result;

import android.support.annotation.Nullable;

import com.proxerme.library.interfaces.ProxerResult;

/**
 * The result of {@link com.proxerme.library.connection.messenger.request.SetFavourRequest},
 * {@link com.proxerme.library.connection.messenger.request.SetUnfavourRequest},
 * {@link com.proxerme.library.connection.messenger.request.SetBlockRequest},
 * {@link com.proxerme.library.connection.messenger.request.SetUnblockRequest}
 * {@link com.proxerme.library.connection.messenger.request.SetUnreadRequest} or
 * {@link com.proxerme.library.connection.messenger.request.SetReportRequest}.
 *
 * This class is just a placeholder for the specified Requests and will never hold any information.
 *
 * @author Desnoo
 */
public class VoidResult implements ProxerResult<Void> {

    public VoidResult() {
    }

    @Override
    @Nullable
    public Void getItem() {
        return null;
    }
}
