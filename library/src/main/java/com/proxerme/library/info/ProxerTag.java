package com.proxerme.library.info;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This class contains all the different Tags for each API.
 */
public class ProxerTag {

    public static final int NEWS = 0;
    public static final int LOGIN = 1;
    public static final int LOGOUT = 2;
    public static final int USERINFO = 3;
    public static final int TOPTEN = 4;
    public static final int USER_MEDIA_LIST = 5;
    public static final int MEDIA_LIST = 6;
    public static final int MEDIA_SEARCH = 7;

    public static final int INFO_ENTRY_CORE = 20;
    public static final int INFO_ENTRY_SYNONYM = 21;
    public static final int INFO_ENTRY_SEASON = 22;


    public static final int MESSENGER_CONFERENCES = 30;
    public static final int MESSENGER_MESSAGES = 31;

    public static final int CONFERENCES = 100;
    public static final int CHAT = 101;
    public static final int SEND_MESSAGE = 102;

    /**
     * An annotation representing all the different tags.
     */
    @IntDef({LOGIN, NEWS, LOGOUT, USERINFO, TOPTEN, CONFERENCES, CHAT, SEND_MESSAGE,
            USER_MEDIA_LIST, MEDIA_LIST, MEDIA_SEARCH,
            INFO_ENTRY_CORE, INFO_ENTRY_SYNONYM, INFO_ENTRY_SEASON,
            MESSENGER_CONFERENCES, MESSENGER_MESSAGES})
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    public @interface ConnectionTag {
    }
}
