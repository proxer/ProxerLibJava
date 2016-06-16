package com.proxerme.library.connection.notifications.entitiy;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.proxerme.library.interfaces.TimeItem;

/**
 * A entity holding all relevant info of a single news.
 *
 * @author Ruben Gees
 */
public class News implements Parcelable, IdItem, TimeItem, ImageItem {

    public static final Creator<News> CREATOR = new Creator<News>() {
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        public News[] newArray(int size) {
            return new News[size];
        }
    };

    @Body(name = "nid")
    public String id;
    @Body(name = "time")
    public long time;
    @Body(name = "description")
    public String description;
    @Body(name = "image_id")
    public String imageId;
    @Body(name = "subject")
    public String subject;
    @Body(name = "hits")
    public int hits;
    @Body(name = "thread")
    public String threadId;
    @Body(name = "uid")
    public String authorId;
    @Body(name = "uname")
    public String author;
    @Body(name = "posts")
    public int posts;
    @Body(name = "catid")
    public String categoryId;
    @Body(name = "carname")
    public String categoryTitle;

    /**
     * Only for automatic conversion, don't use
     */
    public News() {

    }

    /**
     * @param id            The id of the News.
     * @param time          The time the News was published.
     * @param description   A description.
     * @param imageId       The image.
     * @param subject       The subject of the News.
     * @param hits          The amount of views.
     * @param threadId      The id of the thread.
     * @param authorId      The user id of the author.
     * @param author        The name of the author.
     * @param posts         The amount of comments on the News.
     * @param categoryId    The id of the category this News is in.
     * @param categoryTitle The title of the category.
     */
    public News(@NonNull String id, long time, @NonNull String description, @NonNull String imageId,
                @NonNull String subject, @IntRange(from = 0) int hits, @NonNull String threadId,
                @NonNull String authorId, @NonNull String author, @IntRange(from = 0) int posts,
                @NonNull String categoryId, @NonNull String categoryTitle) {
        this.id = id;
        this.time = time;
        this.description = description;
        this.imageId = imageId;
        this.subject = subject;
        this.hits = hits;
        this.threadId = threadId;
        this.authorId = authorId;
        this.author = author;
        this.posts = posts;
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
    }

    protected News(Parcel in) {
        this.id = in.readString();
        this.time = in.readLong();
        this.description = in.readString();
        this.imageId = in.readString();
        this.subject = in.readString();
        this.hits = in.readInt();
        this.threadId = in.readString();
        this.authorId = in.readString();
        this.author = in.readString();
        this.posts = in.readInt();
        this.categoryId = in.readString();
        this.categoryTitle = in.readString();
    }

    /**
     * Returns the id of this News.
     *
     * @return The id.
     */
    @NonNull
    @Override
    public String getId() {
        return id;
    }

    /**
     * returns the time this News has been published.
     *
     * @return The time as a unix-timestamp.
     */
    @Override
    public long getTime() {
        return time;
    }

    /**
     * Returns the description of this News.
     *
     * @return The description.
     */
    @NonNull
    public String getDescription() {
        return description;
    }

    /**
     * Returns the image of this News.
     *
     * @return The image.
     */
    @NonNull
    @Override
    public String getImageId() {
        return imageId;
    }

    /**
     * Return the subject of this News.
     *
     * @return The subject.
     */
    @NonNull
    public String getSubject() {
        return subject;
    }

    /**
     * Returns the amount of views of this news.
     *
     * @return The amount of views.
     */
    @IntRange(from = 0)
    public int getHits() {
        return hits;
    }

    /**
     * Returns the id of the thread.
     *
     * @return The id of the thread.
     */
    @NonNull
    public String getThreadId() {
        return threadId;
    }

    /**
     * Returns the user id of the author.
     *
     * @return The id.
     */
    @NonNull
    public String getAuthorId() {
        return authorId;
    }

    /**
     * Returns the username of the author.
     *
     * @return The username.
     */
    @NonNull
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the amount of comments on this News.
     *
     * @return the amount of comments.
     */
    @IntRange(from = 0)
    public int getPosts() {
        return posts;
    }

    /**
     * Returns the id of the category of this News.
     *
     * @return The id.
     */
    @NonNull
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * Returns the title of the category of this News.
     *
     * @return The title of the category.
     */
    @NonNull
    public String getCategoryTitle() {
        return categoryTitle;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (time != news.time) return false;
        if (hits != news.hits) return false;
        if (posts != news.posts) return false;
        if (!id.equals(news.id)) return false;
        if (!description.equals(news.description)) return false;
        if (!imageId.equals(news.imageId)) return false;
        if (!subject.equals(news.subject)) return false;
        if (!threadId.equals(news.threadId)) return false;
        if (!authorId.equals(news.authorId)) return false;
        if (!author.equals(news.author)) return false;
        if (!categoryId.equals(news.categoryId)) return false;
        return categoryTitle.equals(news.categoryTitle);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + description.hashCode();
        result = 31 * result + imageId.hashCode();
        result = 31 * result + subject.hashCode();
        result = 31 * result + hits;
        result = 31 * result + threadId.hashCode();
        result = 31 * result + authorId.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + posts;
        result = 31 * result + categoryId.hashCode();
        result = 31 * result + categoryTitle.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeLong(this.time);
        dest.writeString(this.description);
        dest.writeString(this.imageId);
        dest.writeString(this.subject);
        dest.writeInt(this.hits);
        dest.writeString(this.threadId);
        dest.writeString(this.authorId);
        dest.writeString(this.author);
        dest.writeInt(this.posts);
        dest.writeString(this.categoryId);
        dest.writeString(this.categoryTitle);
    }
}
