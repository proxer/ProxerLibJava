package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.chat.ChatMessage

/**
 * Enum holding the possible actions of a [ChatMessage].
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
@FallbackEnum(name = "NONE")
enum class ChatMessageAction {

    @Json(name = "")
    NONE,

    @Json(name = "removeMessage")
    REMOVE_MESSAGE
}
