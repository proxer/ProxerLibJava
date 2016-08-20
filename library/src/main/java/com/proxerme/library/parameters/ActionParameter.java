package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the available actions of a
 * {@link com.proxerme.library.connection.messenger.entity.Message}.
 *
 * @author Ruben Gees
 */

public class ActionParameter {

    public static final String NONE = "";
    public static final String ADD_USER = "addUser";
    public static final String REMOVE_USER = "removeUser";
    public static final String SET_LEADER = "setLeader";
    public static final String SET_TOPIC = "setTopic";

    /**
     * An annotation representing the available actions of a
     * {@link com.proxerme.library.connection.messenger.entity.Message}.
     */
    @StringDef({NONE, ADD_USER, REMOVE_USER, SET_LEADER, SET_TOPIC})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface Action {
    }

}
