package com.proxerme.library.connection;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Todo: Describe Class
 *
 * @author Ruben Gees
 */
public class ProxerTag {
    public static final int NEWS = 0;
    public static final int NEWS_SYNC = 1;
    public static final int LOGIN = 2;
    public static final int LOGIN_SYNC = 3;
    public static final int LOGOUT = 4;
    public static final int LOGOUT_SYNC = 5;
    public static final int CONFERENCES = 6;
    public static final int CONFERENCES_SYNC = 7;

    @IntDef({LOGIN, LOGIN_SYNC, NEWS, NEWS_SYNC, LOGOUT, LOGOUT_SYNC, CONFERENCES,
            CONFERENCES_SYNC})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    public @interface ConnectionTag {
    }
}
