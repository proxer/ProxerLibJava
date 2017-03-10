package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the possible actions of a {@link com.proxerme.library.entitiy.messenger.Message}.
 *
 * @author Ruben Gees
 */
public enum MessageAction {
    @Json(name = "")NONE,
    @Json(name = "addUser")ADD_USER,
    @Json(name = "removeUser")REMOVE_USER,
    @Json(name = "setLeader")SET_LEADER,
    @Json(name = "setTopic")SET_TOPIC
}
