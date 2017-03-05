package com.proxerme.library.entitiy.notifications;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.proxerme.library.interfaces.TimeItem;
import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * Entity holding all relevant info of a single news article.
 *
 * @author Ruben Gees
 */
public final class NewsArticle implements IdItem, TimeItem, ImageItem {

    @Json(name = "nid")
    private String id;
    @Json(name = "time")
    private Date time;
    @Json(name = "description")
    private String description;
    @Json(name = "image_id")
    private String imageId;
    @Json(name = "subject")
    private String subject;
    @Json(name = "hits")
    private int hits;
    @Json(name = "thread")
    private String threadId;
    @Json(name = "uid")
    private String authorId;
    @Json(name = "uname")
    private String author;
    @Json(name = "posts")
    private int commentAmount;
    @Json(name = "catid")
    private String categoryId;
    @Json(name = "catname")
    private String categoryTitle;

    private NewsArticle() {

    }

    /**
     * @param id            The id of the news article.
     * @param time          The time the news article was published.
     * @param description   A description.
     * @param imageId       The image.
     * @param subject       The subject of the news article.
     * @param hits          The amount of views.
     * @param threadId      The id of the thread.
     * @param authorId      The user id of the author.
     * @param author        The name of the author.
     * @param commentAmount The amount of comments on the news article.
     * @param categoryId    The id of the category this news article is in.
     * @param categoryTitle The title of the category.
     */
    public NewsArticle(@NotNull final String id, @NotNull final Date time, @NotNull final String description,
                       @NotNull final String imageId, @NotNull final String subject, final int hits,
                       @NotNull final String threadId, @NotNull final String authorId, @NotNull final String author,
                       final int commentAmount, @NotNull final String categoryId, @NotNull final String categoryTitle) {
        this.id = id;
        this.time = time;
        this.description = description;
        this.imageId = imageId;
        this.subject = subject;
        this.hits = hits;
        this.threadId = threadId;
        this.authorId = authorId;
        this.author = author;
        this.commentAmount = commentAmount;
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
    }

    /**
     * Returns the id of this news article.
     *
     * @return The id.
     */
    @Override
    @NotNull
    public String getId() {
        return id;
    }

    /**
     * returns the time this news article has been published.
     *
     * @return The time as a unix timestamp.
     */
    @Override
    @NotNull
    public Date getTime() {
        return time;
    }

    /**
     * Returns the description of this news article.
     *
     * @return The description.
     */
    @NotNull
    public String getDescription() {
        return description;
    }

    /**
     * Returns the image of this news article.
     *
     * @return The image.
     */
    @Override
    @NotNull
    public String getImageId() {
        return imageId;
    }

    /**
     * Return the subject of this news article.
     *
     * @return The subject.
     */
    @NotNull
    public String getSubject() {
        return subject;
    }

    /**
     * Returns the amount of views of this news article.
     *
     * @return The amount of views.
     */
    public int getHits() {
        return hits;
    }

    /**
     * Returns the id of the thread.
     *
     * @return The id of the thread.
     */
    @NotNull
    public String getThreadId() {
        return threadId;
    }

    /**
     * Returns the user id of the author.
     *
     * @return The id.
     */
    @NotNull
    public String getAuthorId() {
        return authorId;
    }

    /**
     * Returns the username of the author.
     *
     * @return The username.
     */
    @NotNull
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the amount of comments on this news article.
     *
     * @return the amount of comments.
     */
    public int getCommentAmount() {
        return commentAmount;
    }

    /**
     * Returns the id of the category of this news article.
     *
     * @return The id.
     */
    @NotNull
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * Returns the title of the category of this news article.
     *
     * @return The title of the category.
     */
    @NotNull
    public String getCategoryTitle() {
        return categoryTitle;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsArticle that = (NewsArticle) o;

        if (hits != that.hits) return false;
        if (commentAmount != that.commentAmount) return false;
        if (!id.equals(that.id)) return false;
        if (!time.equals(that.time)) return false;
        if (!description.equals(that.description)) return false;
        if (!imageId.equals(that.imageId)) return false;
        if (!subject.equals(that.subject)) return false;
        if (!threadId.equals(that.threadId)) return false;
        if (!authorId.equals(that.authorId)) return false;
        if (!author.equals(that.author)) return false;
        if (!categoryId.equals(that.categoryId)) return false;
        return categoryTitle.equals(that.categoryTitle);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + time.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + imageId.hashCode();
        result = 31 * result + subject.hashCode();
        result = 31 * result + hits;
        result = 31 * result + threadId.hashCode();
        result = 31 * result + authorId.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + commentAmount;
        result = 31 * result + categoryId.hashCode();
        result = 31 * result + categoryTitle.hashCode();
        return result;
    }
}
