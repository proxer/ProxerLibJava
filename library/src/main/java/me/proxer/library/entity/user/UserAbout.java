package me.proxer.library.entity.user;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Value;
import me.proxer.library.enums.Gender;
import me.proxer.library.enums.RelationshipStatus;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * Entity holding all info of a user.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class UserAbout {

    /**
     * Returns the website of the user. Can be empty, a link or a name.
     */
    @Json(name = "info_website")
    private String website;

    /**
     * Returns the occupation of the user. Can be empty.
     */
    @Json(name = "info_occupation")
    private String occupation;

    /**
     * Returns the interests of the user. Can be empty.
     */
    @Json(name = "info_interests")
    private String interests;

    /**
     * Returns the city of the user. Can be empty.
     */
    @Json(name = "info_city")
    private String city;

    /**
     * Returns the country of the user. Can be empty.
     */
    @Json(name = "info_country")
    private String country;

    /**
     * Returns the about text of the user. Can be empty and can contain HTML-Tags.
     */
    @Json(name = "info_about")
    private String about;

    /**
     * Returns the facebook info of the user. Can be empty, a link or a name.
     */
    @Json(name = "info_facebook")
    private String facebook;

    /**
     * Returns the youtube info of the user. Can be empty, a link or a name.
     */
    @Json(name = "info_youtube")
    private String youtube;

    /**
     * Returns the chatango info of the user. Can be empty, a link or a name.
     */
    @Json(name = "info_chatango")
    private String chatango;

    /**
     * Returns the twitter info of the user. Can be empty, a link or a name.
     */
    @Json(name = "info_twitter")
    private String twitter;

    /**
     * Returns the skype info of the user. Can be empty, a link or a name.
     */
    @Json(name = "info_skype")
    private String skype;

    /**
     * Returns the deviantart info of the user. Can be empty, a link or a name.
     */
    @Json(name = "info_deviantart")
    private String deviantart;

    /**
     * Returns the birthday of the user. Can be an actual date or 00.00.0000. The format is dd.MM.yyyy.
     */
    @Json(name = "info_birthday")
    private Date birthday;

    /**
     * Returns the gender of the user.
     */
    @Json(name = "info_gender")
    private Gender gender;

    /**
     * Returns the relationship status of the user.
     */
    @Json(name = "info_relationshipstatus")
    private RelationshipStatus relationshipStatus;
}
