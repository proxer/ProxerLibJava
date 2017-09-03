package me.proxer.library.enums;

import com.squareup.moshi.Json;
import me.proxer.library.entity.messenger.Message;

/**
 * Enum holding the possible actions of a {@link Message}.
 *
 * @author Ruben Gees
 */
public enum MessageAction {
    @Json(name = "") NONE,
    @Json(name = "addUser") ADD_USER,
    @Json(name = "removeUser") REMOVE_USER,
    @Json(name = "setLeader") SET_LEADER,
    @Json(name = "setTopic") SET_TOPIC
}
