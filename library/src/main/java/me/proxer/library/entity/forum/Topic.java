package me.proxer.library.entity.forum;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;

/**
 * Class representing a topic in the forum.
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class Topic {

    /**
     * Returns the id of the category this topic resides in.
     */
    @Json(name = "category_id")
    private String categoryId;

    /**
     * Returns the name of the category this topic resides in.
     */
    @Json(name = "category_name")
    private String categoryName;

    /**
     * Returns the subject of this topic.
     */
    @Json(name = "subject")
    private String subject;

    /**
     * Returns if this topic is locked.
     */
    @Json(name = "locked")
    private boolean locked;

    /**
     * Returns the amount of posts in this topic.
     */
    @Json(name = "post_count")
    private int postAmount;

    /**
     * Returns the hits of this topic.
     */
    @Json(name = "hits")
    private int hits;

    /**
     * Returns the date of the first post in this topic.
     */
    @Json(name = "first_post_time")
    private Date firstPostDate;

    /**
     * Returns the date of the first post in this topic.
     */
    @Json(name = "last_post_time")
    private Date lastPostDate;

    /**
     * Returns the posts of this topic.
     */
    @Json(name = "posts")
    private List<Post> posts;
}
