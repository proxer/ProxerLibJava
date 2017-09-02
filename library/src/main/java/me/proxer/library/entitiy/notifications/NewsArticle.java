package me.proxer.library.entitiy.notifications;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerDateItem;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.entitiy.ProxerImageItem;

import java.util.Date;

/**
 * Entity holding all relevant info of a single news article.
 *
 * @author Ruben Gees
 */
@Value
public class NewsArticle implements ProxerIdItem, ProxerDateItem, ProxerImageItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "nid")
    private String id;

    /**
     * Returns the time.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "time")
    private Date date;

    /**
     * Returns the description.
     */
    @Json(name = "description")
    private String description;

    /**
     * Returns the image id.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "image_id")
    private String image;

    /**
     * Returns the subject.
     */
    @Json(name = "subject")
    private String subject;

    /**
     * Returns the amount of clicks by users.
     */
    @Json(name = "hits")
    private int hits;

    /**
     * Returns the thread id.
     */
    @Json(name = "thread")
    private String threadId;

    /**
     * Returns the id of the author.
     */
    @Json(name = "uid")
    private String authorId;

    /**
     * Returns the username of the author.
     */
    @Json(name = "uname")
    private String author;

    /**
     * Returns the amount of comments.
     */
    @Json(name = "posts")
    private int commentAmount;

    /**
     * Returns the id of the category.
     */
    @Json(name = "catid")
    private String categoryId;

    /**
     * Returns the name of the category.
     */
    @Json(name = "catname")
    private String category;
}
