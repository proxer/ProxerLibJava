package me.proxer.library.internal.adapter

import me.proxer.library.entity.messenger.Conference
import me.proxer.library.internal.adapter.ConferenceAdapter.IntermediateConference
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class ConferenceAdapterTest {

    private val intermediateConferenceWithImage = IntermediateConference(
        id = "123", topic = "something", customTopic = "somethingElse", participantAmount = 13, isGroup = true,
        isRead = false, date = Date(123456L), unreadMessageAmount = 2, lastReadMessageId = "321",
        rawImage = "avatar:http://example.com/image.png"
    )

    private val conferenceWithImage = Conference(
        id = "123", topic = "something", customTopic = "somethingElse", participantAmount = 13,
        image = "http://example.com/image.png", imageType = "avatar", isGroup = true, isRead = false,
        date = Date(123456L), unreadMessageAmount = 2, lastReadMessageId = "321"
    )

    private val intermediateConferenceWithoutImage = intermediateConferenceWithImage.copy(rawImage = "")
    private val conferenceWithoutImage = conferenceWithImage.copy(image = "", imageType = "")

    private val adapter = ConferenceAdapter()

    @Test
    fun testFromJson() {
        adapter.fromJson(intermediateConferenceWithImage) shouldBeEqualTo conferenceWithImage
    }

    @Test
    fun testFromJsonWithoutImage() {
        adapter.fromJson(intermediateConferenceWithoutImage) shouldBeEqualTo conferenceWithoutImage
    }
}
