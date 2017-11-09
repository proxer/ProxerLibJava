package me.proxer.library.entity.info;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.annotation.Nullable;

/**
 * Entity which holds info of the connection between an {@link Entry} and an {@link me.proxer.library.entity.user.User}.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class MediaUserInfo {

    /**
     * Returns true, if this media has been noted by the user.
     */
    @Json(name = "noted")
    private boolean noted;

    /**
     * Returns true, if this media has been finished by the user.
     */
    @Json(name = "finished")
    private boolean finished;

    /**
     * Returns true, if this media has been canceled by the user.
     */
    @Json(name = "canceled")
    private boolean canceled;

    /**
     * Returns true, if this media is on the top ten list of the user.
     */
    @Json(name = "topten")
    private boolean topTen;
}
