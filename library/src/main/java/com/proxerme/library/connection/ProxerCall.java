package com.proxerme.library.connection;

import okhttp3.Call;

/**
 * TODO: Describe class
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
