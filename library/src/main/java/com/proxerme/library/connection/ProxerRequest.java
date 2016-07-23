package com.proxerme.library.connection;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.WorkerThread;

import com.afollestad.bridge.Bridge;
import com.afollestad.bridge.BridgeException;
import com.afollestad.bridge.Callback;
import com.afollestad.bridge.Form;
import com.afollestad.bridge.Request;
import com.afollestad.bridge.Response;
import com.afollestad.bridge.ResponseValidator;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.interfaces.ProxerErrorResult;
import com.proxerme.library.interfaces.ProxerResult;


public abstract class ProxerRequest<R extends ProxerResult> {

    private Handler handler;

    public ProxerRequest() {
        handler = new Handler(Looper.getMainLooper());
    }

    /**
     * Returns the {@link ProxerTag} of this request.
     *
     * @return The {@link ProxerTag}.
     */
    @ProxerTag.ConnectionTag
    protected abstract int getTag();

    protected abstract R parse(Response response) throws Exception;

    @NonNull
    protected abstract String getURL();

    /**
     * Asynchronously executes this request. The result will be delivered on the event bus
     * method.
     *
     * @see #executeSynchronized()
     */
    @RequiresPermission(android.Manifest.permission.INTERNET)
    public final Request execute(@Nullable final ProxerCallback<R> callback,
                                 @Nullable final ProxerErrorCallback errorCallback) {
        return Bridge.post(getURL(), (Object[]) getParameters()).body(buildBody())
                .throwIfNotSuccess().tag(String.valueOf(getTag())).validators(getValidator())
                .request(new Callback() {
                    @Override
                    public void response(@NonNull Request request, Response response,
                                         BridgeException exception) {
                        if (exception == null) {
                            try {
                                deliverResultOnMainThread(callback, parse(response));
                            } catch (BridgeException e) {
                                deliverErrorResultOnMainThread(errorCallback,
                                        new ProxerErrorResult(ProxerErrorHandler
                                                .handleException(e)));
                            } catch (Exception e) {
                                deliverErrorResultOnMainThread(errorCallback,
                                        new ProxerErrorResult(
                                                new ProxerException(ProxerException.UNPARSEABLE)));
                            }
                        } else {
                            if (exception.reason() != BridgeException.REASON_REQUEST_CANCELLED) {
                                deliverErrorResultOnMainThread(errorCallback,
                                        new ProxerErrorResult(ProxerErrorHandler
                                                .handleException(exception)));
                            }
                        }
                    }
                });
    }

    @WorkerThread
    @RequiresPermission(android.Manifest.permission.INTERNET)
    public final R executeSynchronized() throws ProxerException {
        try {
            return parse(Bridge.post(getURL(), (Object[]) getParameters()).body(buildBody())
                    .throwIfNotSuccess().tag(getTag()).validators(getValidator()).request()
                    .response());
        } catch (BridgeException e) {
            throw ProxerErrorHandler.handleException(e);
        } catch (Exception e) {
            throw new ProxerException(ProxerException.UNPARSEABLE);
        }
    }

    @Nullable
    protected String[] getParameters() {
        return null;
    }

    protected void appendToBody(@NonNull Form form) {

    }

    @NonNull
    protected ResponseValidator getValidator() {
        return new DefaultValidator();
    }

    @NonNull
    private Form buildBody() {
        Form body = new Form();

        body.add("api_key", ProxerConnection.getKey());
        appendToBody(body);

        return body;
    }

    private void deliverResultOnMainThread(@Nullable final ProxerCallback<R> callback,
                                           final R result) {
        if (callback != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callback.onSuccess(result);
                }
            });
        }
    }

    private void deliverErrorResultOnMainThread(@Nullable final ProxerErrorCallback callback,
                                                final ProxerErrorResult errorResult) {
        if (callback != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callback.onError(errorResult);
                }
            });
        }
    }

    public interface ProxerCallback<R extends ProxerResult> {
        void onSuccess(R result);
    }

    public interface ProxerErrorCallback {
        void onError(ProxerErrorResult result);
    }
}
