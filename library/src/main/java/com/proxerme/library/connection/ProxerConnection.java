package com.proxerme.library.connection;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CheckResult;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.WorkerThread;

import com.afollestad.bridge.Bridge;
import com.afollestad.bridge.BridgeException;
import com.afollestad.bridge.Form;
import com.afollestad.bridge.RequestBuilder;
import com.afollestad.bridge.Response;
import com.afollestad.bridge.ResponseConvertCallback;
import com.afollestad.bridge.ResponseValidator;
import com.proxerme.library.entity.Conference;
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

import org.json.JSONObject;

import java.util.Iterator;
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

    private static <R extends ProxerResult, ER extends ProxerErrorResult>
    void deliverResultOnMainThread(final ProxerCallback<R, ER> callback, final R result) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(result);
            }
        });
    }

    private static <R extends ProxerResult, ER extends ProxerErrorResult>
    void deliverErrorResultOnMainThread(final ProxerCallback<R, ER> callback, final ER errorResult) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                callback.onError(errorResult);
            }
        });
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
            buildRequest().throwIfNotSuccess().tag(String.valueOf(getTag()))
                    .asClass(getResultClass(), new ResponseConvertCallback<T>() {
                @Override
                public void onResponse(@NonNull Response response, @Nullable T result,
                                       @Nullable BridgeException exception) {
                    if (exception == null) {
                        if (result != null) {
                            deliverResultOnMainThread(callback,
                                    createResult(result));
                        } else {
                            deliverErrorResultOnMainThread(callback,
                                    createErrorResult(new ProxerException(ERROR_UNKNOWN)));
                        }
                    } else {
                        if (exception.reason() != BridgeException.REASON_REQUEST_CANCELLED) {
                            deliverErrorResultOnMainThread(callback,
                                    createErrorResult(ProxerErrorHandler.handleException(exception)));
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
                T result = buildRequest().tag(getTag() + 1).asClass(getResultClass());

                if (result == null) {
                    throw new ProxerException(ERROR_UNKNOWN);
                } else {
                    return result;
                }
            } catch (BridgeException e) {
                throw ProxerErrorHandler.handleException(e);
            }
        }

        protected abstract R createResult(@NonNull T result);

        protected abstract RE createErrorResult(@NonNull ProxerException exception);

        protected abstract Class<T> getResultClass();
    }

    /**
     * A request, returning a List of {@link News}.
     */
    public static class NewsRequest extends ProxerRequest<News[], NewsResult, NewsErrorResult> {

        private int page;

        public NewsRequest(@IntRange(from = 1) int page) {
            this.page = page;
        }

        @NonNull
        @Override
        protected RequestBuilder buildRequest() {
            return Bridge.get(ProxerUrlHolder.getNewsUrl(page)).validators(defaultValidator);
        }

        @ConnectionTag
        @Override
        protected int getTag() {
            return NEWS;
        }

        @Override
        protected NewsResult createResult(@NonNull News[] result) {
            return new NewsResult(result);
        }

        @Override
        protected NewsErrorResult createErrorResult(@NonNull ProxerException exception) {
            return new NewsErrorResult(exception);
        }

        @Override
        protected Class<News[]> getResultClass() {
            return News[].class;
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

            return Bridge.post(ProxerUrlHolder.getLoginUrl()).body(loginCredentials)
                    .validators(loginLogoutValidator);
        }

        @ConnectionTag
        @Override
        protected int getTag() {
            return LOGIN;
        }

        @Override
        protected LoginResult createResult(@NonNull LoginUser result) {
            return new LoginResult(result);
        }

        @Override
        protected LoginErrorResult createErrorResult(@NonNull ProxerException exception) {
            return new LoginErrorResult(exception);
        }

        @Override
        protected Class<LoginUser> getResultClass() {
            return LoginUser.class;
        }
    }

    /**
     * A request for the logout.
     */
    public static class LogoutRequest extends ProxerRequest<Void, LogoutResult, LogoutErrorResult> {

        @NonNull
        @Override
        protected RequestBuilder buildRequest() {
            return Bridge.get(ProxerUrlHolder.getLogoutUrl()).validators(loginLogoutValidator);
        }

        @ConnectionTag
        @Override
        protected int getTag() {
            return LOGOUT;
        }

        @Override
        protected LogoutResult createResult(@NonNull Void result) {
            return new LogoutResult();
        }

        @Override
        protected LogoutErrorResult createErrorResult(@NonNull ProxerException exception) {
            return new LogoutErrorResult(exception);
        }

        @Override
        protected Class<Void> getResultClass() {
            return Void.class;
        }
    }

    /**
     * A request for retrieval of the {@link Conference}s of a user.
     */
    public static class ConferencesRequest extends ProxerRequest<Conference[],
            ConferencesResult, ConferencesErrorResult> {

        private int page;

        public ConferencesRequest(@IntRange(from = 1) int page) {
            this.page = page;
        }

        @NonNull
        @Override
        protected RequestBuilder buildRequest() {
            return Bridge.get(ProxerUrlHolder.getConferencesUrl(page)).validators(defaultValidator);
        }

        @Override
        protected int getTag() {
            return CONFERENCES;
        }

        @Override
        protected ConferencesResult createResult(@NonNull Conference[] result) {
            return new ConferencesResult(result);
        }

        @Override
        protected ConferencesErrorResult createErrorResult(@NonNull ProxerException exception) {
            return new ConferencesErrorResult(exception);
        }

        @Override
        protected Class<Conference[]> getResultClass() {
            return Conference[].class;
        }
    }

    /**
     * A request for retrieval of the {@link Message}s in a specific {@link Conference}.
     */
    public static class MessagesRequest extends ProxerRequest<Message[], MessagesResult,
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
            return Bridge.get(ProxerUrlHolder.getMessagesUrl(conferenceId, page))
                    .validators(defaultValidator);
        }

        @Override
        protected int getTag() {
            return MESSAGES;
        }

        @Override
        protected MessagesResult createResult(@NonNull Message[] result) {
            return new MessagesResult(conferenceId, result);
        }

        @Override
        protected MessagesErrorResult createErrorResult(@NonNull ProxerException exception) {
            return new MessagesErrorResult(conferenceId, exception);
        }

        @Override
        protected Class<Message[]> getResultClass() {
            return Message[].class;
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

            return Bridge.post(ProxerUrlHolder.getSendMessageUrl(conferenceId)).body(messageForm)
                    .validators(defaultValidator);
        }

        @Override
        protected int getTag() {
            return ProxerTag.SEND_MESSAGE;
        }

        @Override
        protected MessageSentResult createResult(@NonNull Void result) {
            return new MessageSentResult(conferenceId);
        }

        @Override
        protected SendingMessageErrorResult createErrorResult(@NonNull ProxerException exception) {
            return new SendingMessageErrorResult(conferenceId, exception);
        }

        @Override
        protected Class<Void> getResultClass() {
            return Void.class;
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