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

    public static final int LOGIN = 10;
    public static final int LOGOUT = 11;

    public static final int USER_INFO = 20;
    public static final int USER_TOPTEN = 21;
    public static final int USER_MEDIA_LIST = 22;

    public static final int MEDIA_LIST = 30;
    public static final int MEDIA_SEARCH = 31;

    public static final int INFO_ENTRY_CORE = 40;
    public static final int INFO_ENTRY_SYNONYM = 41;
    public static final int INFO_ENTRY_SEASON = 42;

    public static final int MESSENGER_CONFERENCES = 50;
    public static final int MESSENGER_MESSAGES = 51;
    public static final int MESSENGER_SEND_MESSAGE = 52;
    public static final int MESSENGER_CONFERENCE_CONSTANTS = 53;
    public static final int MESSENGER_CONFERENCE_INFO = 54;
    public static final int MESSENGER_SET_FAVOUR = 55;
    public static final int MESSENGER_SET_UNFAVOUR = 56;
    public static final int MESSENGER_SET_BLOCK = 57;
    public static final int MESSENGER_SET_UNBLOCK = 58;
    public static final int MESSENGER_SET_UNREAD = 59;
    public static final int MESSENGER_SET_REPORT = 60;

    /**
     * An annotation representing all the different tags.
     */
    @IntDef({LOGIN, NEWS, LOGOUT, USER_INFO, USER_TOPTEN, USER_MEDIA_LIST, MEDIA_LIST, MEDIA_SEARCH,
            INFO_ENTRY_CORE, INFO_ENTRY_SYNONYM, INFO_ENTRY_SEASON, MESSENGER_CONFERENCES,
            MESSENGER_MESSAGES, MESSENGER_SEND_MESSAGE, MESSENGER_CONFERENCE_CONSTANTS,
            MESSENGER_CONFERENCE_INFO, MESSENGER_SET_FAVOUR, MESSENGER_SET_UNFAVOUR,
            MESSENGER_CONFERENCE_INFO, MESSENGER_SET_FAVOUR, MESSENGER_SET_UNFAVOUR, MESSENGER_SET_BLOCK,
            MESSENGER_SET_UNBLOCK, MESSENGER_SET_UNREAD, MESSENGER_SET_REPORT})
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    public @interface ConnectionTag {
    }
}
