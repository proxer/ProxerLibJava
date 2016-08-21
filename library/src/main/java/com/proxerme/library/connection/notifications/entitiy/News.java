package com.proxerme.library.connection.notifications.entitiy;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.proxerme.library.interfaces.TimeItem;

/**
 * A entity holding all relevant info of a single News.
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

    String nid;
    long time;
    String description;
    String image_id;
    String subject;
    int hits;
    String thread;
    String uid;
    String uname;
    int posts;
    String catid;
    String catname;

    /**
     * Only for automatic conversion, don't use
     */
    News() {

    }

    /**
     * @param id            The nid of the News.
     * @param time          The time the News was published.
     * @param description   A description.
     * @param imageId       The image.
     * @param subject       The subject of the News.
     * @param hits          The amount of views.
     * @param threadId      The nid of the thread.
     * @param authorId      The user nid of the uname.
     * @param author        The name of the uname.
     * @param posts         The amount of comments on the News.
     * @param categoryId    The nid of the category this News is in.
     * @param categoryTitle The title of the category.
     */
    public News(@NonNull String id, long time, @NonNull String description, @NonNull String imageId,
                @NonNull String subject, @IntRange(from = 0) int hits, @NonNull String threadId,
                @NonNull String authorId, @NonNull String author, @IntRange(from = 0) int posts,
                @NonNull String categoryId, @NonNull String categoryTitle) {
        this.nid = id;
        this.time = time;
        this.description = description;
        this.image_id = imageId;
        this.subject = subject;
        this.hits = hits;
        this.thread = threadId;
        this.uid = authorId;
        this.uname = author;
        this.posts = posts;
        this.catid = categoryId;
        this.catname = categoryTitle;
    }

    protected News(Parcel in) {
        this.nid = in.readString();
        this.time = in.readLong();
        this.description = in.readString();
        this.image_id = in.readString();
        this.subject = in.readString();
        this.hits = in.readInt();
        this.thread = in.readString();
        this.uid = in.readString();
        this.uname = in.readString();
        this.posts = in.readInt();
        this.catid = in.readString();
        this.catname = in.readString();
    }

    /**
     * Returns the nid of this News.
     *
     * @return The nid.
     */
    @NonNull
    @Override
    public String getId() {
        return nid;
    }

    /**
     * returns the time this News has been published.
     *
     * @return The time as a unix timestamp.
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
        return image_id;
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
     * Returns the nid of the thread.
     *
     * @return The nid of the thread.
     */
    @NonNull
    public String getThreadId() {
        return thread;
    }

    /**
     * Returns the user nid of the uname.
     *
     * @return The nid.
     */
    @NonNull
    public String getAuthorId() {
        return uid;
    }

    /**
     * Returns the username of the uname.
     *
     * @return The username.
     */
    @NonNull
    public String getAuthor() {
        return uname;
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
     * Returns the nid of the category of this News.
     *
     * @return The nid.
     */
    @NonNull
    public String getCategoryId() {
        return catid;
    }

    /**
     * Returns the title of the category of this News.
     *
     * @return The title of the category.
     */
    @NonNull
    public String getCategoryTitle() {
        return catname;
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
        if (!nid.equals(news.nid)) return false;
        if (!description.equals(news.description)) return false;
        if (!image_id.equals(news.image_id)) return false;
        if (!subject.equals(news.subject)) return false;
        if (!thread.equals(news.thread)) return false;
        if (!uid.equals(news.uid)) return false;
        if (!uname.equals(news.uname)) return false;
        if (!catid.equals(news.catid)) return false;
        return catname.equals(news.catname);
    }

    @Override
    public int hashCode() {
        int result = nid.hashCode();
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + description.hashCode();
        result = 31 * result + image_id.hashCode();
        result = 31 * result + subject.hashCode();
        result = 31 * result + hits;
        result = 31 * result + thread.hashCode();
        result = 31 * result + uid.hashCode();
        result = 31 * result + uname.hashCode();
        result = 31 * result + posts;
        result = 31 * result + catid.hashCode();
        result = 31 * result + catname.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nid);
        dest.writeLong(this.time);
        dest.writeString(this.description);
        dest.writeString(this.image_id);
        dest.writeString(this.subject);
        dest.writeInt(this.hits);
        dest.writeString(this.thread);
        dest.writeString(this.uid);
        dest.writeString(this.uname);
        dest.writeInt(this.posts);
        dest.writeString(this.catid);
        dest.writeString(this.catname);
    }
}
