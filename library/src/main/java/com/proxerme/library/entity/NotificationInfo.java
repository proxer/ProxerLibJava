package com.proxerme.library.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;

/**
 * Todo: Describe Class
 *
 * @author Ruben Gees
 */
public class NotificationInfo implements Parcelable {

    public static final Parcelable.Creator<NotificationInfo> CREATOR = new Parcelable.Creator<NotificationInfo>() {
        public NotificationInfo createFromParcel(Parcel source) {
            return new NotificationInfo(source);
        }

        public NotificationInfo[] newArray(int size) {
            return new NotificationInfo[size];
        }
    };
    private int messages;
    private int friendRequests;
    private int news;
    private int others;

    public NotificationInfo(@IntRange(from = 0) int messages,
                            @IntRange(from = 0) int friendRequests, @IntRange(from = 0) int news,
                            @IntRange(from = 0) int others) {
        this.messages = messages;
        this.friendRequests = friendRequests;
        this.news = news;
        this.others = others;
    }

    protected NotificationInfo(Parcel in) {
        this.messages = in.readInt();
        this.friendRequests = in.readInt();
        this.news = in.readInt();
        this.others = in.readInt();
    }

    @IntRange(from = 0)
    public int getMessages() {
        return messages;
    }

    @IntRange(from = 0)
    public int getFriendRequests() {
        return friendRequests;
    }

    @IntRange(from = 0)
    public int getNews() {
        return news;
    }

    @IntRange(from = 0)
    public int getOthers() {
        return others;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotificationInfo that = (NotificationInfo) o;

        if (messages != that.messages) return false;
        if (friendRequests != that.friendRequests) return false;
        if (news != that.news) return false;
        return others == that.others;

    }

    @Override
    public int hashCode() {
        int result = messages;
        result = 31 * result + friendRequests;
        result = 31 * result + news;
        result = 31 * result + others;
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.messages);
        dest.writeInt(this.friendRequests);
        dest.writeInt(this.news);
        dest.writeInt(this.others);
    }
}
