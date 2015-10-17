package com.proxerme.library.connection;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.proxerme.library.entity.Conference;
import com.proxerme.library.entity.LoginData;
import com.proxerme.library.entity.LoginUser;
import com.proxerme.library.entity.News;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.proxerme.library.connection.ErrorHandler.ErrorCodes.PROXER;
import static com.proxerme.library.connection.ErrorHandler.ErrorCodes.UNKNOWN;

/**
 * Todo: Describe Class
 *
 * @author Ruben Gees
 */
public class ProxerConnection {
    public static final String ERROR = "error";
    public static final String MESSAGE = "message";
    private static final String FORM_USERNAME = "username";
    private static final String FORM_PASSWORD = "password";
    private static ProxerConnection INSTANCE;
    private Context context;
    private RequestQueue queue;

    private ProxerConnection(@NonNull Context context) {
        this.context = context;
        getRequestQueue();
    }

    public static ProxerConnection getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ProxerConnection(context);
        }
        return INSTANCE;
    }

    private RequestQueue getRequestQueue() {
        if (queue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            queue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return queue;
    }

    public void getNews(@IntRange(from = 1) int page,
                        @NonNull ResultCallback<List<News>> callback) {
        new NewsRequest(page, callback).execute();
    }

    public void getConferences(@IntRange(from = 1) int page,
                               @NonNull ResultCallback<List<Conference>> callback) {
        new ConferencesRequest(page, callback).execute();
    }

    public void login(@NonNull LoginUser user,
                      @NonNull ResultCallback<LoginUser> callback) {
        new LoginRequest(user, callback).execute();
    }

    public void logout(@NonNull ResultCallback<Void> callback) {
        new LogoutRequest(callback).execute();
    }

    public void cancel(@ProxerTag.ConnectionTag int tag) {
        queue.cancelAll(tag);
    }

    public void cancelAll() {
        queue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
    }

    public static abstract class ResultCallback<T> {
        public void onResult(T result) {

        }

        public void onError(@NonNull ProxerException exception) {

        }
    }

    private abstract class ProxerRequest<T> {
        private ResultCallback<T> callback;

        public ProxerRequest(ResultCallback<T> callback) {
            this.callback = callback;
        }

        public void execute() {
            queue.add(getRequest(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        ProxerException validationException = validateResponse(response);

                        if (validationException != null) {
                            callback.onError(validationException);
                        }
                        callback.onResult(parseResponse(response));
                    } catch (JSONException exception) {
                        callback.onError(ErrorHandler.handleException(exception));
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.onError(ErrorHandler.handleException(error));
                }
            }));
        }

        private ProxerException validateResponse(@NonNull JSONObject object) throws JSONException {
            ProxerException result = null;

            if (object.has(ERROR)) {
                if (object.getInt(ERROR) != 0) {
                    if (object.has(MESSAGE)) {
                        result = new ProxerException(PROXER,
                                object.getString(MESSAGE));
                    } else {
                        result = new ProxerException(UNKNOWN);
                    }
                }
            } else {
                result = new ProxerException(UNKNOWN);
            }

            return result;
        }

        protected abstract JsonRequest getRequest(@NonNull Response.Listener<JSONObject> listener,
                                                  Response.ErrorListener errorListener);

        protected abstract T parseResponse(@NonNull JSONObject object) throws JSONException;
    }

    private class NewsRequest extends ProxerRequest<List<News>> {

        private int page;

        public NewsRequest(@IntRange(from = 1) int page, ResultCallback<List<News>> callback) {
            super(callback);
            this.page = page;
        }

        @Override
        protected JsonRequest getRequest(@NonNull Response.Listener<JSONObject> listener,
                                         Response.ErrorListener errorListener) {
            PriorityJsonObjectRequest request = new PriorityJsonObjectRequest(Request.Method.GET,
                    UrlHolder.getNewsUrl(page), listener, errorListener);

            request.setPriority(Request.Priority.NORMAL).setShouldCache(false)
                    .setTag(ProxerTag.NEWS);
            return request;
        }

        @Override
        protected List<News> parseResponse(@NonNull JSONObject object) throws JSONException {
            return ProxerParser.parseNewsJSON(object);
        }
    }

    private class ConferencesRequest extends ProxerRequest<List<Conference>> {

        private int page;

        public ConferencesRequest(@IntRange(from = 1) int page,
                                  ResultCallback<List<Conference>> callback) {
            super(callback);
            this.page = page;
        }

        @Override
        protected JsonRequest getRequest(@NonNull Response.Listener<JSONObject> listener,
                                         Response.ErrorListener errorListener) {
            PriorityJsonObjectRequest request = new PriorityJsonObjectRequest(Request.Method.GET,
                    UrlHolder.getConferencesUrl(page), listener, errorListener);

            request.setPriority(Request.Priority.NORMAL).setShouldCache(false)
                    .setTag(ProxerTag.CONFERENCES);
            return request;
        }

        @Override
        protected List<Conference> parseResponse(@NonNull JSONObject object) throws JSONException {
            return ProxerParser.parseConferencesJSON(object);
        }
    }

    private class LoginRequest extends ProxerRequest<LoginUser> {

        private LoginUser user;

        public LoginRequest(LoginUser user, ResultCallback<LoginUser> callback) {
            super(callback);
            this.user = user;
        }

        @Override
        protected JsonRequest getRequest(@NonNull Response.Listener<JSONObject> listener,
                                         Response.ErrorListener errorListener) {
            LoginJsonObjectRequest request = new LoginJsonObjectRequest(user,
                    listener, errorListener);

            request.setPriority(Request.Priority.IMMEDIATE).setShouldCache(false)
                    .setTag(ProxerTag.LOGIN);
            return request;
        }

        @Override
        protected LoginUser parseResponse(@NonNull JSONObject object) throws JSONException {
            LoginData loginData = ProxerParser.parseLoginJSON(object);

            return new LoginUser(user.getUsername(), user.getPassword(), loginData.getId(),
                    loginData.getImageId());
        }
    }

    private class LogoutRequest extends ProxerRequest<Void> {

        public LogoutRequest(ResultCallback<Void> callback) {
            super(callback);
        }

        @Override
        protected JsonRequest getRequest(@NonNull Response.Listener<JSONObject> listener,
                                         Response.ErrorListener errorListener) {
            PriorityJsonObjectRequest request = new PriorityJsonObjectRequest(
                    UrlHolder.getLogoutUrl(), listener, errorListener);

            request.setPriority(Request.Priority.IMMEDIATE).setShouldCache(false)
                    .setTag(ProxerTag.LOGOUT);
            return request;
        }

        @Override
        protected Void parseResponse(@NonNull JSONObject object) throws JSONException {
            return null;
        }
    }

    private class PriorityJsonObjectRequest extends JsonObjectRequest {

        private Priority priority;

        public PriorityJsonObjectRequest(int method, String url, String requestBody,
                                         Response.Listener<JSONObject> listener,
                                         Response.ErrorListener errorListener) {
            super(method, url, requestBody, listener, errorListener);
        }

        public PriorityJsonObjectRequest(String url, Response.Listener<JSONObject> listener,
                                         Response.ErrorListener errorListener) {
            super(url, listener, errorListener);
        }

        public PriorityJsonObjectRequest(int method, String url,
                                         Response.Listener<JSONObject> listener,
                                         Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        public PriorityJsonObjectRequest(int method, String url, JSONObject jsonRequest,
                                         Response.Listener<JSONObject> listener,
                                         Response.ErrorListener errorListener) {
            super(method, url, jsonRequest, listener, errorListener);
        }

        public PriorityJsonObjectRequest(String url, JSONObject jsonRequest,
                                         Response.Listener<JSONObject> listener,
                                         Response.ErrorListener errorListener) {
            super(url, jsonRequest, listener, errorListener);
        }

        @Override
        public Priority getPriority() {
            return priority;
        }

        public PriorityJsonObjectRequest setPriority(Priority priority) {
            this.priority = priority;

            return this;
        }
    }

    private class LoginJsonObjectRequest extends PriorityJsonObjectRequest {

        private Map<String, String> params;

        public LoginJsonObjectRequest(LoginUser user, Response.Listener<JSONObject> listener,
                                      Response.ErrorListener errorListener) {
            super(UrlHolder.getLoginUrl(), listener, errorListener);

            params = new HashMap<>(2);
            params.put(FORM_USERNAME, user.getUsername());
            params.put(FORM_PASSWORD, user.getPassword());
        }

        @Override
        public int getMethod() {
            return Method.POST;
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return params;
        }
    }
}
