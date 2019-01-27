package me.proxer.library.entity.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.enums.Gender
import me.proxer.library.enums.RelationshipStatus

/**
 * Entity holding all info of a user.
 *
 * @property website The website of the user. Can be empty, a link or a name.
 * @property occupation The occupation of the user. Can be empty.
 * @property interests The interests of the user. Can be empty.
 * @property city The city of the user. Can be empty.
 * @property country The country of the user. Can be empty.
 * @property about The about text of the user. Can be empty and can contain HTML-Tags.
 * @property facebook The facebook info of the user. Can be empty, a link or a name.
 * @property youtube The youtube info of the user. Can be empty, a link or a name.
 * @property chatango The chatango info of the user. Can be empty, a link or a name.
 * @property twitter The twitter info of the user. Can be empty, a link or a name.
 * @property skype The skype info of the user. Can be empty, a link or a name.
 * @property deviantart The deviantart info of the user. Can be empty, a link or a name.
 * @property birthday The birthday of the user. Can be an actual date or 00.00.0000. The format is dd.MM.yyyy.
 * @property gender The gender of the user.
 * @property gender The relationship status of the user.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class UserAbout(
    @Json(name = "info_website") val website: String,
    @Json(name = "info_occupation") val occupation: String,
    @Json(name = "info_interests") val interests: String,
    @Json(name = "info_city") val city: String,
    @Json(name = "info_country") val country: String,
    @Json(name = "info_about") val about: String,
    @Json(name = "info_facebook") val facebook: String,
    @Json(name = "info_youtube") val youtube: String,
    @Json(name = "info_chatango") val chatango: String,
    @Json(name = "info_twitter") val twitter: String,
    @Json(name = "info_skype") val skype: String,
    @Json(name = "info_deviantart") val deviantart: String,
    @Json(name = "info_birthday") val birthday: String,
    @Json(name = "info_gender") val gender: Gender,
    @Json(name = "info_relationshipstatus") val relationshipStatus: RelationshipStatus
)
