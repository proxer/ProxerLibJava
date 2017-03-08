package com.proxerme.library.entitiy.notifications;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.proxerme.library.interfaces.TimeItem;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * Entity holding all relevant info of a single news article.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public class NewsArticle implements IdItem, TimeItem, ImageItem {

    /**
     * @return The id.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "nid")
    private String id;

    /**
     * @return The time.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "time")
    private Date time;

    /**
     * @return The description.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "description")
    private String description;

    /**
     * @return The image id.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "image_id")
    private String imageId;

    /**
     * @return The subject.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "subject")
    private String subject;

    /**
     * @return The amount of clicks by users.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "hits")
    private int hits;

    /**
     * @return The thread id.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "thread")
    private String threadId;

    /**
     * @return The id of the author.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "uid")
    private String authorId;

    /**
     * @return The username of the author.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "uname")
    private String author;

    /**
     * @return The amount of comments.
     */
    @Json(name = "posts")
    private int commentAmount;

    /**
     * @return The id of the category.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "catid")
    private String categoryId;

    /**
     * @return The name of the category.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "catname")
    private String category;
}
