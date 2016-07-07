package com.proxerme.library.info;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class ProxerTag {

    public static final int NEWS = 0;
    public static final int LOGIN = 1;
    public static final int LOGOUT = 2;
    public static final int USERINFO = 3;

    public static final int CONFERENCES = 100;
    public static final int MESSAGES = 101;
    public static final int SEND_MESSAGE = 102;

    @IntDef({LOGIN, NEWS, LOGOUT, USERINFO, CONFERENCES, MESSAGES, SEND_MESSAGE})
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    public @interface ConnectionTag {
    }
}
