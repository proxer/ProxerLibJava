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
    public static final int LOGIN = 2;
    public static final int LOGOUT = 4;
    public static final int CONFERENCES = 6;

    @IntDef({LOGIN, NEWS, LOGOUT, CONFERENCES})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    public @interface ConnectionTag {
    }
}