package com.proxerme.library.connection;

import android.support.annotation.IntDef;
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
import com.proxerme.library.entity.LoginData;
import com.proxerme.library.entity.LoginUser;
import com.proxerme.library.entity.News;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import static com.proxerme.library.connection.ErrorHandler.ErrorCodes.PROXER;
import static com.proxerme.library.connection.ErrorHandler.ErrorCodes.UNKNOWN;
import static com.proxerme.library.connection.ErrorHandler.ErrorCodes.UNPARSEABLE;

/**
 * A helper class, which starts all request and manages the {@link Bridge}.
 *
 * @author Ruben Gees
 */
public class ProxerConnection {

    public static final int TAG_NEWS = 0;
    public static final int TAG_NEWS_SYNC = 1;
    public static final int TAG_LOGIN = 2;
    public static final int TAG_LOGIN_SYNC = 3;
    public static final int TAG_LOGOUT = 4;
    public static final int TAG_LOGOUT_SYNC = 5;
    private static final String FORM_USERNAME = "username";
    private static final String FORM_PASSWORD = "password";
    private static final String RESPONSE_ERROR = "error";
    private static final String RESPONSE_ERROR_MESSAGE = "msg";
    private static final String VALIDATOR_ID = "default-validator";

    public static NewsRequest loadNews(@IntRange(from = 1) int page) {
        return new NewsRequest(page);
    }

    public static LoginRequest login(@NonNull final LoginUser user,
                             @NonNull final ResultCallback<LoginUser> callback) {
        return new LoginRequest(user);
    }

    public static LogoutRequest logout(@NonNull final ResultCallback<Void> callback) {
        return new LogoutRequest();
    }

    public static void cancel(@ConnectionTag int tag, boolean force) {
        Bridge.client().cancelAll(tag, force);
    }

    public static void cancelSync(@ConnectionTag int tag, boolean force) {
        Bridge.client().cancelAllSync(tag, force);
    }

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

    public static void cleanup() {
        Bridge.cleanup();
    }

    public interface ResultCallback<T> {
        void onResult(T result);

        void onError(@NonNull ProxerException exception);
    }

    @IntDef({TAG_LOGIN, TAG_LOGIN_SYNC, TAG_NEWS, TAG_NEWS_SYNC, TAG_LOGOUT, TAG_LOGOUT_SYNC})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    public @interface ConnectionTag {
    }

    public static abstract class ProxerRequest<T> {

        @NonNull
        protected abstract RequestBuilder buildRequest(Bridge bridge);

        @ConnectionTag
        protected abstract int getTag();

        public void execute(@NonNull final ResultCallback<T> callback) {
            buildRequest(Bridge.client()).tag(getTag()).request(new Callback() {
                @Override
                public void response(Request request, Response response, BridgeException exception) {
                    if (exception == null) {
                        try {
                            callback.onResult(parse(response.asJsonObject()));
                        } catch (JSONException e) {
                            callback.onError(new ProxerException(UNPARSEABLE));
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

        protected abstract T parse(@NonNull JSONObject response) throws JSONException;
    }

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

        @Override
        protected int getTag() {
            return TAG_NEWS;
        }

        @Override
        protected List<News> parse(@NonNull JSONObject response) throws JSONException {
            return ProxerParser.parseNewsJSON(response);
        }
    }

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

        @Override
        protected int getTag() {
            return TAG_LOGIN;
        }

        @Override
        protected LoginUser parse(@NonNull JSONObject response) throws JSONException {
            LoginData data = ProxerParser.parseLoginJSON(response);

            return new LoginUser(user.getUsername(), user.getPassword(), data.getId(),
                    data.getImageLink());
        }
    }

    public static class LogoutRequest extends ProxerRequest<Void> {

        @NonNull
        @Override
        protected RequestBuilder buildRequest(Bridge bridge) {
            return bridge.get(UrlHolder.getLogoutUrl());
        }

        @Override
        protected int getTag() {
            return TAG_LOGOUT;
        }

        @Override
        protected Void parse(@NonNull JSONObject response) throws JSONException {
            return null;
        }
    }
}
