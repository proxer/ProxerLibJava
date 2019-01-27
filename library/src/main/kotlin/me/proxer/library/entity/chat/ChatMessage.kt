package me.proxer.library.entity.chat

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerDateItem
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.entity.ProxerImageItem
import me.proxer.library.enums.ChatMessageAction
import me.proxer.library.enums.MessageAction
import java.util.Date

/**
 * Entity representing a single message.
 *
 * @property userId The id of the associated user.
 * @property username The username of the author.
 * @property message The actual content of the message.
 * @property action The action of this message. If the action is not [MessageAction.NONE],
 * [message] returns associated information, like the name of the user, performing the action.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class ChatMessage(
    @Json(name = "id") override val id: String,
    @Json(name = "fromid") val userId: String,
    @Json(name = "username") val username: String,
    @Json(name = "avatar") override val image: String,
    @Json(name = "message") val message: String,
    @Json(name = "action") val action: ChatMessageAction,
    @Json(name = "timestamp") override val date: Date
) : ProxerIdItem, ProxerDateItem, ProxerImageItem
