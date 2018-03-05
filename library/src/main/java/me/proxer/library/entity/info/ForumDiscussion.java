package me.proxer.library.entity.info;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerIdItem;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * Entity containing meta information about a single forum discussion.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class ForumDiscussion implements ProxerIdItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the id of the category.
     */
    @Json(name = "category_id")
    private String categoryId;

    /**
     * Returns the of the category.
     */
    @Json(name = "category_name")
    private String category;

    /**
     * Returns the subject.
     */
    @Json(name = "subject")
    private String subject;

    /**
     * Returns the amount of posts.
     */
    @Json(name = "posts")
    private int posts;

    /**
     * Returns the amount of hits.
     */
    @Json(name = "hits")
    private int hits;

    /**
     * Returns the date of the first post.
     */
    @Json(name = "first_post_time")
    private Date firstPostDate;

    /**
     * Returns the user id of the author of the first post.
     */
    @Json(name = "first_post_userid")
    private String firstPostUserId;

    /**
     * Returns the username of the author of the first post.
     */
    @Json(name = "first_post_guest_name")
    private String firstPostUsername;

    /**
     * Returns the date of the last post.
     */
    @Json(name = "last_post_time")
    private Date lastPostDate;

    /**
     * Returns the user id of the author of the last post.
     */
    @Json(name = "last_post_userid")
    private String lastPostUserId;

    /**
     * Returns the username of the author of the last post.
     */
    @Json(name = "last_post_guest_name")
    private String lastPostUsername;
}
