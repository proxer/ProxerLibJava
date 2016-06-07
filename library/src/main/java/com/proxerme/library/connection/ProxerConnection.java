package com.proxerme.library.connection;

import android.support.annotation.CheckResult;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.annotation.WorkerThread;

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
import com.proxerme.library.entity.Message;
import com.proxerme.library.entity.News;
import com.proxerme.library.result.ProxerResult;
import com.proxerme.library.result.error.ConferencesErrorResult;
import com.proxerme.library.result.error.LoginErrorResult;
import com.proxerme.library.result.error.LogoutErrorResult;
import com.proxerme.library.result.error.MessagesErrorResult;
import com.proxerme.library.result.error.NewsErrorResult;
import com.proxerme.library.result.error.ProxerErrorResult;
import com.proxerme.library.result.error.SendingMessageErrorResult;
import com.proxerme.library.result.success.ConferencesResult;
import com.proxerme.library.result.success.LoginResult;
import com.proxerme.library.result.success.LogoutResult;
import com.proxerme.library.result.success.MessageSentResult;
import com.proxerme.library.result.success.MessagesResult;
import com.proxerme.library.result.success.NewsResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.proxerme.library.connection.ProxerException.ERROR_PROXER;
import static com.proxerme.library.connection.ProxerException.ERROR_UNKNOWN;
import static com.proxerme.library.connection.ProxerTag.CONFERENCES;
import static com.proxerme.library.connection.ProxerTag.ConnectionTag;
import static com.proxerme.library.connection.ProxerTag.LOGIN;
import static com.proxerme.library.connection.ProxerTag.LOGOUT;
import static com.proxerme.library.connection.ProxerTag.MESSAGES;
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
    private static final String RESPONSE_ERROR_MESSAGE = "msg";
    private static final String RESPONSE_ERROR_MESSAGE_LOGIN_LOGOUT = "message";
    private static final String VALIDATOR_ID = "default-validator";
    private static final String LOGIN_LOGOUT_VALIDATOR_ID = "login-logout-validator";

    private static final ResponseValidator defaultValidator = new ResponseValidator() {
        @Override
        public boolean validate(@NonNull Response response) throws Exception {
            JSONObject json = response.asJsonObject();

            if (json != null && json.has(RESPONSE_ERROR)) {
                if (json.getInt(RESPONSE_ERROR) == 0) {
                    return true;
                } else {
                    if (json.has(RESPONSE_ERROR_MESSAGE)) {
                        throw new ProxerException(ERROR_PROXER,
                                json.getString(RESPONSE_ERROR_MESSAGE));
                    } else {
                        throw new ProxerException(ERROR_UNKNOWN);
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
    };

    private static final ResponseValidator loginLogoutValidator = new ResponseValidator() {
        @Override
        public boolean validate(@NonNull Response response) throws Exception {
            JSONObject json = response.asJsonObject();

            if (json != null && json.has(RESPONSE_ERROR)) {
                if (json.getInt(RESPONSE_ERROR) == 0) {
                    return true;
                } else {
                    if (json.has(RESPONSE_ERROR_MESSAGE_LOGIN_LOGOUT)) {
                        throw new ProxerException(ERROR_PROXER,
                                json.getString(RESPONSE_ERROR_MESSAGE_LOGIN_LOGOUT));
                    } else {
                        throw new ProxerException(ERROR_UNKNOWN);
                    }
                }
            } else {
                return false;
            }
        }

        @NonNull
        @Override
        public String id() {
            return LOGIN_LOGOUT_VALIDATOR_ID;
        }
    };

    private static final ConcurrentLinkedQueue<ParseThread> parseThreads =
            new ConcurrentLinkedQueue<>();

    /**
     * Entry point to load News of a specified page.
     *
     * @param page The page to load the News.
     * @return A {@link NewsRequest} to work with.
     */
    @NonNull
    @CheckResult
    public static NewsRequest loadNews(@IntRange(from = 1) int page) {
        return new NewsRequest(page);
    }

    /**
     * Entry point for the login.
     *
     * @param user The User to login with.
     * @return A {@link LoginRequest} to work with.
     */
    @NonNull
    @CheckResult
    public static LoginRequest login(@NonNull LoginUser user) {
        return new LoginRequest(user);
    }

    /**
     * Entry point for the logout.
     *
     * @return A {@link LogoutRequest} to work with.
     */
    @NonNull
    @CheckResult
    public static LogoutRequest logout() {
        return new LogoutRequest();
    }

    /**
     * Entry point to load {@link Conference}s of the specified page.
     *
     * @param page The page to load the {@link Conference}s
     * @return A {@link ConferencesRequest} to work with.
     */
    @NonNull
    @CheckResult
    public static ConferencesRequest loadConferences(@IntRange(from = 1) int page) {
        return new ConferencesRequest(page);
    }

    /**
     * Entry point to load the Messages in a {@link Conference}, based on paging.
     *
     * @param conferenceId The id of the {@link Conference}.
     * @param page         The page (Note: This Api starts with 0 and not with 1).
     * @return A {@link MessagesRequest} to work with.
     */
    @NonNull
    @CheckResult
    public static MessagesRequest loadMessages(@NonNull String conferenceId,
                                               @IntRange(from = 0) int page) {
        return new MessagesRequest(conferenceId, page);
    }

    /**
     * Entry point for sending a single message to a {@link Conference}.
     * Note that this Api is not official and might change at any time.
     *
     * @param conferenceId The id of the {@link Conference}.
     * @param message      The message to send
     * @return A {@link SendMessageRequest} to work with.
     */
    @NonNull
    @CheckResult
    public static SendMessageRequest sendMessage(@NonNull String conferenceId,
                                                 @NonNull String message) {
        return new SendMessageRequest(conferenceId, message);
    }

    /**
     * Cancels all started requests of the specified tag.
     *
     * @param tag The {@link ProxerTag} to cancel
     * @see ProxerTag
     */
    public static void cancel(@ConnectionTag int tag) {
        synchronized (parseThreads) {
            Iterator<ParseThread> iterator = parseThreads.iterator();

            while (iterator.hasNext()) {
                ParseThread current = iterator.next();

                if (current.getTag() == tag) {
                    current.interrupt();
                    iterator.remove();
                }
            }

            Bridge.cancelAll().tag(String.valueOf(tag)).commit();
        }
    }

    /**
     * Cleans up references and left open connections. You should call this method somewhere in the
     * lifecycle, but don't have to. A good place might be the onDestroy method of your main
     * Activity.
     */
    public static void cleanup() {
        for (Thread parseThread : parseThreads) {
            parseThread.interrupt();
        }

        parseThreads.clear();
        Bridge.destroy();
    }

    /**
     * An abstract representation of a request. All requests to the API are made through this class.
     *
     * @param <T> The type of result the inheriting request will return.
     */
    public static abstract class ProxerRequest<T, R extends ProxerResult, RE extends ProxerErrorResult> {

        /**
         * Builds the request, to be used in the
         * {@link #execute(ProxerCallback)} or
         * {@link #executeSynchronized()} method.
         *
         * @return The {@link RequestBuilder} to use for further invocations.
         */
        @NonNull
        protected abstract RequestBuilder buildRequest();

        /**
         * Returns the {@link ProxerTag} of this request.
         *
         * @return The {@link ProxerTag}.
         */
        @ConnectionTag
        protected abstract int getTag();

        /**
         * Asynchronously executes this request. The result will be delivered on the event bus
         * method.
         *
         * @see #executeSynchronized()
         */
        @RequiresPermission(android.Manifest.permission.INTERNET)
        public final void execute(final ProxerCallback<R, RE> callback) {
            buildRequest().throwIfNotSuccess().tag(String.valueOf(getTag())).request(new Callback() {
                @Override
                public void response(Request request, final Response response,
                                     BridgeException exception) {
                    if (exception == null) {
                        ParseThread parseThread = new ParseThread(getTag(), new ThreadedRunnable() {
                            @Override
                            public void run() {
                                try {
                                    JSONObject json = response.asJsonObject();

                                    if (json == null) {
                                        callback.onError(createErrorResult(
                                                new ProxerException(ERROR_UNKNOWN)));
                                    } else {
                                        callback.onSuccess(createResult(parse(json)));
                                    }
                                } catch (final JSONException e) {
                                    callback.onError(createErrorResult(ErrorHandler
                                            .handleException(e)));
                                } catch (final BridgeException e) {
                                    callback.onError(createErrorResult(ErrorHandler
                                            .handleException(e)));
                                }

                                parseThreads.remove(getThread());
                            }
                        });

                        parseThreads.add(parseThread);
                        parseThread.start();
                    } else {
                        if (exception.reason() != BridgeException.REASON_REQUEST_CANCELLED) {
                            callback.onError(createErrorResult(ErrorHandler
                                    .handleException(exception)));
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
        public final T executeSynchronized() throws ProxerException {
            try {
                JSONObject result = buildRequest().tag(getTag() + 1).asJsonObject();

                if (result == null) {
                    throw new ProxerException(ERROR_UNKNOWN);
                } else {
                    return parse(result);
                }
            } catch (JSONException e) {
                throw ErrorHandler.handleException(e);
            } catch (BridgeException e) {
                throw ErrorHandler.handleException(e);
            }
        }

        /**
         * Parses the raw response of the server.
         *
         * @param response The response in JSON format.
         * @return The specific type of result of this class.
         * @throws JSONException An Exception, which might occur while parsing.
         */
        protected abstract T parse(@NonNull JSONObject response) throws JSONException;

        protected abstract R createResult(@NonNull T result);

        protected abstract RE createErrorResult(@NonNull ProxerException exception);
    }

    /**
     * A request, returning a List of {@link News}.
     */
    public static class NewsRequest extends ProxerRequest<List<News>, NewsResult, NewsErrorResult> {

        private int page;

        public NewsRequest(@IntRange(from = 1) int page) {
            this.page = page;
        }

        @NonNull
        @Override
        protected RequestBuilder buildRequest() {
            return Bridge.get(UrlHolder.getNewsUrl(page)).validators(defaultValidator);
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

        @Override
        protected NewsResult createResult(@NonNull List<News> result) {
            return new NewsResult(result);
        }

        @Override
        protected NewsErrorResult createErrorResult(@NonNull ProxerException exception) {
            return new NewsErrorResult(exception);
        }
    }

    /**
     * A request for the login, returning a {@link LoginUser} on success.
     */
    public static class LoginRequest extends ProxerRequest<LoginUser, LoginResult, LoginErrorResult> {

        private LoginUser user;

        public LoginRequest(@NonNull LoginUser user) {
            this.user = user;
        }

        @NonNull
        @Override
        protected RequestBuilder buildRequest() {
            Form loginCredentials = new Form().add(FORM_USERNAME, user.getUsername())
                    .add(FORM_PASSWORD, user.getPassword());

            return Bridge.post(UrlHolder.getLoginUrl()).body(loginCredentials)
                    .validators(loginLogoutValidator);
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

        @Override
        protected LoginResult createResult(@NonNull LoginUser result) {
            return new LoginResult(result);
        }

        @Override
        protected LoginErrorResult createErrorResult(@NonNull ProxerException exception) {
            return new LoginErrorResult(exception);
        }
    }

    /**
     * A request for the logout.
     */
    public static class LogoutRequest extends ProxerRequest<Void, LogoutResult, LogoutErrorResult> {

        @NonNull
        @Override
        protected RequestBuilder buildRequest() {
            return Bridge.get(UrlHolder.getLogoutUrl()).validators(loginLogoutValidator);
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

        @Override
        protected LogoutResult createResult(@NonNull Void result) {
            return new LogoutResult();
        }

        @Override
        protected LogoutErrorResult createErrorResult(@NonNull ProxerException exception) {
            return new LogoutErrorResult(exception);
        }
    }

    /**
     * A request for retrieval of the {@link Conference}s of a user.
     */
    public static class ConferencesRequest extends ProxerRequest<List<Conference>,
            ConferencesResult, ConferencesErrorResult> {

        private int page;

        public ConferencesRequest(@IntRange(from = 1) int page) {
            this.page = page;
        }

        @NonNull
        @Override
        protected RequestBuilder buildRequest() {
            return Bridge.get(UrlHolder.getConferencesUrl(page)).validators(defaultValidator);
        }

        @Override
        protected int getTag() {
            return CONFERENCES;
        }

        @Override
        protected List<Conference> parse(@NonNull JSONObject response) throws JSONException {
            return ProxerParser.parseConferencesJSON(response);
        }

        @Override
        protected ConferencesResult createResult(@NonNull List<Conference> result) {
            return new ConferencesResult(result);
        }

        @Override
        protected ConferencesErrorResult createErrorResult(@NonNull ProxerException exception) {
            return new ConferencesErrorResult(exception);
        }
    }

    /**
     * A request for retrieval of the {@link Message}s in a specific {@link Conference}.
     */
    public static class MessagesRequest extends ProxerRequest<List<Message>, MessagesResult,
            MessagesErrorResult> {

        private String conferenceId;
        private int page;

        public MessagesRequest(@NonNull String conferenceId, @IntRange(from = 0) int page) {
            this.conferenceId = conferenceId;
            this.page = page;
        }

        @NonNull
        @Override
        protected RequestBuilder buildRequest() {
            return Bridge.get(UrlHolder.getMessagesUrl(conferenceId, page))
                    .validators(defaultValidator);
        }

        @Override
        protected int getTag() {
            return MESSAGES;
        }

        @Override
        protected List<Message> parse(@NonNull JSONObject response) throws JSONException {
            return ProxerParser.parseMessagesJSON(response);
        }

        @Override
        protected MessagesResult createResult(@NonNull List<Message> result) {
            return new MessagesResult(conferenceId, result);
        }

        @Override
        protected MessagesErrorResult createErrorResult(@NonNull ProxerException exception) {
            return new MessagesErrorResult(conferenceId, exception);
        }
    }

    /**
     * A request for sending a message to a specific {@link Conference}.
     */
    public static class SendMessageRequest extends ProxerRequest<Void, MessageSentResult,
            SendingMessageErrorResult> {

        private String conferenceId;
        private String message;

        public SendMessageRequest(@NonNull String conferenceId, @NonNull String message) {
            this.conferenceId = conferenceId;
            this.message = message;
        }

        @NonNull
        @Override
        protected RequestBuilder buildRequest() {
            Form messageForm = new Form();

            messageForm.add("message", message);

            return Bridge.post(UrlHolder.getSendMessageUrl(conferenceId)).body(messageForm)
                    .validators(defaultValidator);
        }

        @Override
        protected int getTag() {
            return ProxerTag.SEND_MESSAGE;
        }

        @Override
        protected Void parse(@NonNull JSONObject response) throws JSONException {
            return null;
        }

        @Override
        protected MessageSentResult createResult(@NonNull Void result) {
            return new MessageSentResult(conferenceId);
        }

        @Override
        protected SendingMessageErrorResult createErrorResult(@NonNull ProxerException exception) {
            return new SendingMessageErrorResult(conferenceId, exception);
        }
    }

    private static class ParseThread extends Thread {

        @ConnectionTag
        private int tag;

        public ParseThread(@ConnectionTag int tag, ThreadedRunnable runnable) {
            super(runnable);

            this.tag = tag;
            runnable.setThread(this);
        }

        @ConnectionTag
        public int getTag() {
            return tag;
        }
    }

    private abstract static class ThreadedRunnable implements Runnable {
        private ParseThread thread;

        public ParseThread getThread() {
            return thread;
        }

        public void setThread(ParseThread thread) {
            this.thread = thread;
        }
    }

    public abstract static class ProxerCallback<R extends ProxerResult,
            ER extends ProxerErrorResult> {
        public void onSuccess(R result) {

        }

        public void onError(ER result) {

        }
    }
}