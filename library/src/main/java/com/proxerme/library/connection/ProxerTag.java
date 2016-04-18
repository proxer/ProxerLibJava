package com.proxerme.library.connection;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class which holds all the different tags for request.
 * @see ProxerConnection.ProxerRequest
 *
 * @author Ruben Gees
 */
public class ProxerTag {

    /**
     * Tag for the asynchronous {@link ProxerConnection.NewsRequest}.
     */
    public static final int NEWS = 0;

    /**
     * Tag for the synchronous {@link ProxerConnection.NewsRequest}.
     */
    public static final int NEWS_SYNC = 1;

    /**
     * Tag for the asynchronous {@link ProxerConnection.LoginRequest}.
     */
    public static final int LOGIN = 2;

    /**
     * Tag for the synchronous {@link ProxerConnection.LoginRequest}.
     */
    public static final int LOGIN_SYNC = 3;

    /**
     * Tag for the asynchronous {@link ProxerConnection.LogoutRequest}.
     */
    public static final int LOGOUT = 4;

    /**
     * Tag for the synchronous {@link ProxerConnection.LogoutRequest}.
     */
    public static final int LOGOUT_SYNC = 5;

    /**
     * Tag for the asynchronous {@link ProxerConnection.ConferencesRequest}.
     */
    public static final int CONFERENCES = 6;

    /**
     * Tag for the synchronous {@link ProxerConnection.ConferencesRequest}.
     */
    public static final int CONFERENCES_SYNC = 7;

    /**
     * Tag for the asynchronous {@link ProxerConnection.MessagesRequest}.
     */
    public static final int MESSAGES = 8;

    /**
     * Tag for the synchronous {@link ProxerConnection.MessagesRequest}.
     */
    public static final int MESSAGES_SYNC = 9;

    /**
     * Tag for the asynchronous {@link ProxerConnection.SendMessageRequest}.
     */
    public static final int SEND_MESSAGE = 10;

    /**
     * Tag for the synchronous {@link ProxerConnection.SendMessageRequest}.
     */
    public static final int SEND_MESSAGE_SYNC = 11;

    /**
     * An annotation, specifying the different tags of a request.
     */
    @IntDef({LOGIN, LOGIN_SYNC, NEWS, NEWS_SYNC, LOGOUT, LOGOUT_SYNC, CONFERENCES,
            CONFERENCES_SYNC, MESSAGES, MESSAGES_SYNC, SEND_MESSAGE, SEND_MESSAGE_SYNC})
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    public @interface ConnectionTag {
    }
}
