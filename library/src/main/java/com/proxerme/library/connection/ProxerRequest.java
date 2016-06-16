package com.proxerme.library.connection;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.annotation.WorkerThread;

import com.afollestad.bridge.BridgeException;
import com.afollestad.bridge.Callback;
import com.afollestad.bridge.Request;
import com.afollestad.bridge.RequestBuilder;
import com.afollestad.bridge.Response;
import com.afollestad.bridge.ResponseValidator;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.interfaces.ProxerErrorResult;
import com.proxerme.library.interfaces.ProxerResult;


public abstract class ProxerRequest<R extends ProxerResult, RE extends ProxerErrorResult> {

    private static <R extends ProxerResult, ER extends ProxerErrorResult>
    void deliverResultOnMainThread(final ProxerCallback<R, ER> callback,
                                   final R result) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(result);
            }
        });
    }

    private static <R extends ProxerResult, ER extends ProxerErrorResult>
    void deliverErrorResultOnMainThread(final ProxerCallback<R, ER> callback,
                                        final ER errorResult) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                callback.onError(errorResult);
            }
        });
    }

    /**
     * Returns the {@link ProxerTag} of this request.
     *
     * @return The {@link ProxerTag}.
     */
    @ProxerTag.ConnectionTag
    protected abstract int getTag();

    protected abstract RequestBuilder beginRequest();

    protected abstract R parse(Response response) throws BridgeException;

    protected abstract RE createErrorResult(@NonNull ProxerException exception);

    /**
     * Asynchronously executes this request. The result will be delivered on the event bus
     * method.
     *
     * @see #executeSynchronized()
     */
    @RequiresPermission(android.Manifest.permission.INTERNET)
    public final Request execute(final ProxerCallback<R, RE> callback) {
        return beginRequest().throwIfNotSuccess().tag(getTag()).validators(getValidator())
                .request(new Callback() {
                    @Override
                    public void response(Request request, Response response,
                                         BridgeException exception) {
                        if (exception == null) {
                            try {
                                deliverResultOnMainThread(callback, parse(response));
                            } catch (BridgeException e) {
                                deliverErrorResultOnMainThread(callback, createErrorResult(
                                        ProxerErrorHandler.handleException(e)));
                            }
                        } else {
                            if (exception.reason() != BridgeException.REASON_REQUEST_CANCELLED) {
                                deliverErrorResultOnMainThread(callback, createErrorResult(
                                        ProxerErrorHandler.handleException(exception)));
                            }
                        }
                    }
                });
    }

    /**
     * Synchronously executes this request.
     *
     * @return The result, specified by this class.
     * @throws ProxerException An Exception, which might occur, while executing the request.
     * @see #execute(ProxerCallback)
     */
    @WorkerThread
    @RequiresPermission(android.Manifest.permission.INTERNET)
    public final R executeSynchronized() throws ProxerException {
        try {
            return parse(beginRequest().throwIfNotSuccess().tag(getTag()).request()
                    .response());
        } catch (BridgeException e) {
            throw ProxerErrorHandler.handleException(e);
        }
    }

    @NonNull
    protected ResponseValidator getValidator() {
        return new DefaultValidator();
    }

    public abstract static class ProxerCallback<R extends ProxerResult,
            ER extends ProxerErrorResult> {
        public void onSuccess(R result) {

        }

        public void onError(ER result) {

        }
    }
}
