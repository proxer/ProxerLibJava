package com.proxerme.library.connection;

import okhttp3.Call;

/**
 * Class which allows cancellation of a {@link ProxerRequest}. The {@link ProxerConnection} returns
 * an instance of this class if the
 * {@link ProxerConnection#execute(ProxerRequest, ProxerCallback, ProxerErrorCallback)} method is
 * called.
 *
 * @author Ruben Gees
 */

public class ProxerCall {

    private Call call;

    ProxerCall(Call call) {
        this.call = call;
    }

    public void cancel() {
        call.cancel();
    }

    public boolean isCancelled() {
        return call.isCanceled();
    }

    public boolean isExecuted() {
        return call.isExecuted();
    }
}
