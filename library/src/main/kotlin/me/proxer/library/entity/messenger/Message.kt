package me.proxer.library.entity.messenger

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerDateItem
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.Device
import me.proxer.library.enums.MessageAction
import java.util.Date

/**
 * Entity representing a single message.
 *
 * @property conferenceId The id of the associated [Conference].
 * @property userId The id of the associated user.
 * @property username The username of the author.
 * @property message The actual content of the message.
 * @property action The action of this message. If the action is not [MessageAction.NONE],
 * [message] returns associated information, like the name of the user, performing the action.
 * @property device The device, the message was sent from.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class Message(
    @Json(name = "message_id") override val id: String,
    @Json(name = "conference_id") val conferenceId: String,
    @Json(name = "user_id") val userId: String,
    @Json(name = "username") val username: String,
    @Json(name = "message") val message: String,
    @Json(name = "action") val action: MessageAction,
    @Json(name = "timestamp") override val date: Date,
    @Json(name = "device") val device: Device
) : ProxerIdItem, ProxerDateItem
