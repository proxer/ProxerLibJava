package me.proxer.library.enums;

import com.serjltt.moshi.adapters.FallbackEnum;
import com.squareup.moshi.Json;
import me.proxer.library.entity.chat.ChatMessage;

/**
 * Enum holding the possible actions of a {@link ChatMessage}.
 *
 * @author Ruben Gees
 */
@FallbackEnum(name = "NONE")
public enum ChatMessageAction {
    @Json(name = "") NONE,
    @Json(name = "removeMessage") REMOVE_MESSAGE,
}
