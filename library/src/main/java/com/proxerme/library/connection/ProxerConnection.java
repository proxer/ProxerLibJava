package com.proxerme.library.connection;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.afollestad.bridge.Bridge;
import com.afollestad.bridge.BridgeException;
import com.afollestad.bridge.Callback;
import com.afollestad.bridge.Form;
import com.afollestad.bridge.Request;
import com.afollestad.bridge.RequestBuilder;
import com.afollestad.bridge.Response;
import com.afollestad.bridge.ResponseValidator;
import com.proxerme.library.entity.Conference;
import com.proxerme.library.entity.LoginData;
import com.proxerme.library.entity.LoginUser;
import com.proxerme.library.entity.News;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.proxerme.library.connection.ProxerException.ErrorCodes.PROXER;
import static com.proxerme.library.connection.ProxerException.ErrorCodes.UNKNOWN;
import static com.proxerme.library.connection.ProxerTag.CONFERENCES;
import static com.proxerme.library.connection.ProxerTag.ConnectionTag;
import static com.proxerme.library.connection.ProxerTag.LOGIN;
import static com.proxerme.library.connection.ProxerTag.LOGOUT;
import static com.proxerme.library.connection.ProxerTag.NEWS;

/**
 * Main class to start all requests and manage the networking library.
 *
 * @author Ruben Gees
 */
public class ProxerConnection {
    private static final String FORM_USERNAME = "username";
    private static final String FORM_PASSWORD = "password";
    private static final String RESPONSE_ERROR = "error";
    private static final String RESPONSE_ERROR_MESSAGE = "message";
    private static final String VALIDATOR_ID = "default-validator";

    /**
     * Entry point to load News of a specified page.
     *
     * @param page The page to load the News.
     * @return A {@link NewsRequest} to work with.
     */
    @NonNull
    public static NewsRequest loadNews(@IntRange(from = 1) int page) {
        return new NewsRequest(page);
    }

    /**
     * Entry point for the Login.
     *
     * @param user The User to login with.
     * @return A {@link LoginRequest} to work with.
     */
    @NonNull
    public static LoginRequest login(@NonNull LoginUser user) {
        return new LoginRequest(user);
    }

    /**
     * Entry point for the logout
     *
     * @return A {@link LogoutRequest} to work with.
     */
    @NonNull
    public static LogoutRequest logout() {
        return new LogoutRequest();
    }

    /**
     * Entry point to load Conferences of the specified page.
     *
     * @param page The page to load the Conferences
     * @return A {@link ConferencesRequest} to work with.
     */
    @NonNull
    public static ConferencesRequest loadConferences(@IntRange(from = 1) int page) {
        return new ConferencesRequest(page);
    }

    /**
     * Cancels all asynchronous started requests of the specified tag.
     *
     * @param tag The {@link ProxerTag} to cancel
     * @see ProxerTag
     * @see #cancelSync(int)
     */
    public static void cancel(@ConnectionTag int tag) {
        Bridge.client().cancelAll(tag, false);
    }

    /**
     * Cancels all synchronous started requests of the specified tag.
     *
     * @param tag The {@link ProxerTag} to cancel
     * @see ProxerTag
     * @see #cancel(int)
     */
    public static void cancelSync(@ConnectionTag int tag) {
        Bridge.client().cancelAllSync(tag, false);
    }

    /**
     * Does some initialization steps. You *must* call this method somewhere in your lifecycle. A
     * good place might be the onCreate method of your main Activity.
     */
    public static void init() {
        Bridge.client().config().validators(new ResponseValidator() {
            @Override
            public boolean validate(@NonNull Response response) throws Exception {
                JSONObject json = response.asJsonObject();

                if (json.has(RESPONSE_ERROR)) {
                    if (json.getInt(RESPONSE_ERROR) == 0) {
                        return true;
                    } else {
                        if (json.has(RESPONSE_ERROR_MESSAGE)) {
                            throw new ProxerException(PROXER,
                                    json.getString(RESPONSE_ERROR_MESSAGE));
                        } else {
                            throw new ProxerException(UNKNOWN);
                        }
                    }
                } else {
                    return false;
                }
            }

            @NonNull
            @Override
            public String id() {
                return VALIDATOR_ID;
            }
        });
    }

    /**
     * Cleans up references and left open connections. You should call this method somewhere in the
     * lifecycle, but don't have to. A good place might be the onDestroy method of your main
     * Activity.
     */
    public static void cleanup() {
        Bridge.cleanup();
    }

    /**
     * An abstract representation of a callback, passed to the
     * {@link ProxerRequest#execute(ResultCallback)} method.
     *
     * @param <T> The generic parameter.
     */
    public interface ResultCallback<T> {
        /**
         * A callback method, called if the request was successful.
         * @param result The result of the specific request.
         */
        void onResult(T result);

        /**
         * A callback method, called if an error occurred during the request.
         * @see ProxerException
         * @param exception The Exception that occurred.
         */
        void onError(@NonNull ProxerException exception);
    }

    /**
     * An abstract representation of a request. All requests to the API are made through this class.
     * @param <T> The type of result, the inheriting request will return.
     */
    public static abstract class ProxerRequest<T> {

        /**
         * Builds the request, to be used in the
         * {@link #execute(ResultCallback)} or
         * {@link #executeSynchronized()} method.
         * @param bridge The Bridge instance to build the request with.
         * @return The {@link RequestBuilder} to use for further invocations.
         */
        @NonNull
        protected abstract RequestBuilder buildRequest(Bridge bridge);

        /**
         * Returns the {@link ProxerTag} of this request.
         * @return The {@link ProxerTag}.
         */
        @ConnectionTag
        protected abstract int getTag();

        /**
         * Asynchronously executes this request.
         * @see #executeSynchronized()
         * @param callback The callback for notifications about the Result.
         */
        public void execute(@NonNull final ResultCallback<T> callback) {
            buildRequest(Bridge.client()).tag(getTag()).request(new Callback() {
                @Override
                public void response(Request request, Response response, BridgeException exception) {
                    if (exception == null) {
                        try {
                            callback.onResult(parse(response.asJsonObject()));
                        } catch (JSONException e) {
                            callback.onError(ErrorHandler.handleException(e));
                        } catch (BridgeException e) {
                            callback.onError(ErrorHandler.handleException(e));
                        }
                    } else {
                        if (exception.reason() != BridgeException.REASON_REQUEST_CANCELLED) {
                            callback.onError(ErrorHandler.handleException(exception));
                        }
                    }
                }
            });
        }

        /**
         * Synchronously executes this request.
         * @see #execute(ResultCallback)
         * @return The result, specified by this class.
         * @throws ProxerException An Exception, which might occur, while executing the request.
         */
        public T executeSynchronized() throws ProxerException {
            try {
                JSONObject result = buildRequest(Bridge.client()).tag(getTag() + 1).asJsonObject();

                return parse(result);
            } catch (JSONException e) {
                throw ErrorHandler.handleException(e);
            } catch (BridgeException e) {
                throw ErrorHandler.handleException(e);
            }
        }

        /**
         * Parses the raw response of the server.
         * @param response The response in JSON format.
         * @return The specific type of result of this class.
         * @throws JSONException An Exception, which might occur while parsing.
         */
        protected abstract T parse(@NonNull JSONObject response) throws JSONException;
    }

    /**
     * A request, returning a List of {@link News}.
     */
    public static class NewsRequest extends ProxerRequest<List<News>> {

        private int page;

        public NewsRequest(@IntRange(from = 1) int page) {
            this.page = page;
        }

        @NonNull
        @Override
        protected RequestBuilder buildRequest(Bridge bridge) {
            return bridge.get(UrlHolder.getNewsUrl(page));
        }

        @ConnectionTag
        @Override
        protected int getTag() {
            return NEWS;
        }

        @Override
        protected List<News> parse(@NonNull JSONObject response) throws JSONException {
            return ProxerParser.parseNewsJSON(response);
        }
    }

    /**
     * A request for the login, returning a {@link LoginUser} on success.
     */
    public static class LoginRequest extends ProxerRequest<LoginUser> {

        private LoginUser user;

        public LoginRequest(@NonNull LoginUser user) {
            this.user = user;
        }

        @NonNull
        @Override
        protected RequestBuilder buildRequest(Bridge bridge) {
            Form loginCredentials = new Form().add(FORM_USERNAME, user.getUsername())
                    .add(FORM_PASSWORD, user.getPassword());

            return bridge.post(UrlHolder.getLoginUrl()).body(loginCredentials);
        }

        @ConnectionTag
        @Override
        protected int getTag() {
            return LOGIN;
        }

        @Override
        protected LoginUser parse(@NonNull JSONObject response) throws JSONException {
            LoginData data = ProxerParser.parseLoginJSON(response);

            return new LoginUser(user.getUsername(), user.getPassword(), data.getId(),
                    data.getImageId());
        }
    }

    /**
     * A request for the logout.
     */
    public static class LogoutRequest extends ProxerRequest<Void> {

        @NonNull
        @Override
        protected RequestBuilder buildRequest(Bridge bridge) {
            return bridge.get(UrlHolder.getLogoutUrl());
        }

        @ConnectionTag
        @Override
        protected int getTag() {
            return LOGOUT;
        }

        @Override
        protected Void parse(@NonNull JSONObject response) throws JSONException {
            return null;
        }
    }

    /**
     * A request for retrieval of the {@link Conference}s of a user.
     */
    public static class ConferencesRequest extends ProxerRequest<List<Conference>> {

        private int page;

        public ConferencesRequest(@IntRange(from = 1) int page) {
            this.page = page;
        }

        @NonNull
        @Override
        protected RequestBuilder buildRequest(Bridge bridge) {
            return bridge.get(UrlHolder.getConferencesUrl(page));
        }

        @Override
        protected int getTag() {
            return CONFERENCES;
        }

        @Override
        protected List<Conference> parse(@NonNull JSONObject response) throws JSONException {
            return ProxerParser.parseConferencesJSON(response);
        }
    }
}
