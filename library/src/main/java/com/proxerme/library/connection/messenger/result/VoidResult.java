package com.proxerme.library.connection.messenger.result;

import com.proxerme.library.interfaces.ProxerResult;

/**
 * The result of {@link com.proxerme.library.connection.messenger.request.SetFavourRequest},
 * {@link com.proxerme.library.connection.messenger.request.SetUnfavourRequest},
 * {@link com.proxerme.library.connection.messenger.request.SetBlockRequest},
 * {@link com.proxerme.library.connection.messenger.request.SetUnblockRequest}
 * {@link com.proxerme.library.connection.messenger.request.SetUnreadRequest} or
 * {@link com.proxerme.library.connection.messenger.request.SetReportRequest}.
 *
 * @author Desnoo
 */
public class VoidResult implements ProxerResult<Void> {

    public VoidResult() {
    }

    @Override
    public Void getItem() {
        return null;
    }
}
