package me.proxer.library.entity.forum;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerDateItem;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.entity.ProxerImageItem;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * Class representing a single post of a forum topic.
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class Post implements ProxerIdItem, ProxerImageItem, ProxerDateItem {

    /**
     * Returns the id of this post.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the id of the parent post.
     */
    @Json(name = "pid")
    private String parentId;

    /**
     * Returns the id of the user.
     */
    @Json(name = "uid")
    private String userId;

    /**
     * Returns the name of the user.
     */
    @Json(name = "username")
    private String username;

    /**
     * Returns the image id.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "avatar")
    private String image;

    /**
     * Returns the time, the post was updated the last time.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "time")
    private Date date;

    /**
     * Returns the signature of the user.
     */
    @Nullable
    @Getter
    @Json(name = "signature")
    private String signature;

    /**
     * Returns the id of the last user which modified this post.
     */
    @Nullable
    @Getter
    @Json(name = "modified_by")
    private String modifiedById;

    /**
     * Returns the name of the last user which modified this post.
     */
    @Nullable
    @Getter
    @Json(name = "modified_name")
    private String modifiedByName;

    /**
     * Returns the date of the last modification.
     */
    @Nullable
    @Getter
    @Json(name = "modified_time")
    private Date modifiedDate;

    /**
     * Returns the reason of the last modification.
     */
    @Nullable
    @Getter
    @Json(name = "modified_reason")
    private String modifiedReason;

    /**
     * Returns the actual text of this post.
     */
    @Json(name = "message")
    private String message;

    /**
     * Returns the amount of given "thank you".
     */
    @Json(name = "thank_you_count")
    private int thankYouAmount;

    @SuppressWarnings("checkstyle:parameternumber")
    public Post(final String id, final String parentId, final String userId, final String username, final String image,
                final Date date, @Nullable final String signature, @Nullable final String modifiedById,
                @Nullable final String modifiedByName, @Nullable final Date modifiedDate,
                @Nullable final String modifiedReason, final String message, final int thankYouAmount) {
        this.id = id;
        this.parentId = parentId;
        this.userId = userId;
        this.username = username;
        this.image = image;
        this.date = date;
        this.signature = signature;
        this.modifiedById = modifiedById;
        this.modifiedByName = modifiedByName;
        this.modifiedDate = modifiedDate;
        this.modifiedReason = modifiedReason;
        this.message = message;
        this.thankYouAmount = thankYouAmount;
    }
}
