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

/**
 * The base class for all requests. This class handles the call to the Bridge and delivers the
 * result on the main thread. All inheriting requests need to implement a method for parsing the
 * result, a method which returns the Url and a method which returns a tag, found in the
 * {@link ProxerTag} class.
 * Moreover inheritors can override more methods to allow configuration of the request. This
 * includes the following methods:
 * <p>
 * <ul>
 * <li>
 * {@link #getParameters()} allows to return an array of parameter values to the request.
 * The order of the values is important, as you have to specify placeholders for them in the
 * Url you pass in {@link #getURL()}. This might look like this:
 * https://example.com?param=%s
 * </li>
 * <li>
 * {@link #appendToBody(Form)} allows to append parameters to the body of the request. This
 * might like like this: <code>form.add("param", "value");</code>
 * </li>
 * <li>
 * {@link #getValidator()} allows the use of a custom Validator if the
 * {@link DefaultValidator} does not work for a specific API.
 * </li>
 * </ul>
 * <p>
 * After constructing the class, the user can either call the
 * {@link #execute(ProxerCallback, ProxerErrorCallback)} or the {@link #executeSynchronized()}
 * method. The difference is, that the first is executed asynchronously while the second is executed
 * synchronous. The result of {@link #execute(ProxerCallback, ProxerErrorCallback)} is always
 * delivered on the main thread.
 * <p>
 * A typical usage might look like this:
 * <p>
 * <pre>
 * <code>
 * new ProxerRequest().execute(new ProxerCallback() {
 *     {@literal @}Override
 *     public void onSuccess(ProxerResult result) {
 *         //Do something with the result
 *     }
 * }, new ProxerErrorCallback() {
 *     {@literal @}Override
 *     public void onError(ProxerErrorResult result) {
 *         //Do something with the result
 *     }
 * });
 * </code>
 * </pre>
 *
 * @param <R> The type of result.
 * @author Ruben Gees
 */
public abstract class ProxerRequest<R extends ProxerResult> {

    private static final String FORM_API_KEY = "api_key";

    private Handler handler;

    /**
     * Default constructor.
     */
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

    /**
     * Parses the response and returns a subclass of {@link ProxerResult}, specified by the
     * type parameter.
     *
     * @param response The response.
     * @return The parsed {@link ProxerResult}.
     * @throws Exception If the parsing failed.
     */
    protected abstract R parse(Response response) throws Exception;

    /**
     * Returns the Url for this request. Query parameters might be used in conjunction with the
     * {@link #getParameters()} method.
     *
     * @return The Url.
     */
    @NonNull
    protected abstract String getURL();


    /**
     * Asynchronously executes this request. The result will be delivered on the main thread.
     *
     * @param callback The callback for a successful request.
     * @param errorCallback The callback for a unsuccessful request.
     * @return The Request for further use.
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

    /**
     * Executes the request and returns the result immediately.
     *
     * @return The Result, if no error occurred.
     * @throws ProxerException If an error occurred.
     */
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

    /**
     * Can be overridden to return parameters, encoded in the Url.
     *
     * @return An array of parameters. The order matters.
     */
    @Nullable
    protected String[] getParameters() {
        return null;
    }

    /**
     * Can be overridden to append parameters to the body.
     *
     * @param form The form to append to.
     */
    protected void appendToBody(@NonNull Form form) {

    }

    /**
     * Can be overridden to return a different Validator from the {@link DefaultValidator}.
     *
     * @return The Validator.
     */
    @NonNull
    protected ResponseValidator getValidator() {
        return new DefaultValidator();
    }

    @NonNull
    private Form buildBody() {
        Form body = new Form();

        body.add(FORM_API_KEY, ProxerConnection.getKey());
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

    /**
     * A callback for a successful request.
     *
     * @param <R> The type of the Result.
     */
    public interface ProxerCallback<R extends ProxerResult> {
        void onSuccess(R result);
    }

    /**
     * A callback for a unsuccessful request.
     */
    public interface ProxerErrorCallback {
        void onError(ProxerErrorResult result);
    }
}
