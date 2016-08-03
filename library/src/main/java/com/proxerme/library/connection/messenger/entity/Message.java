package com.proxerme.library.connection.messenger.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.TimeItem;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public class Message implements IdItem, TimeItem, Parcelable {

    public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel source) {
            return new Message(source);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    @Body(name = "message_id")
    String id;
    @Body(name = "conference_id")
    String conferenceId;
    @Body(name = "user_id")
    String userId;
    @Body(name = "message")
    String message;
    @Body(name = "action")
    String action;
    @Body(name = "timestamp")
    long time;
    @Body(name = "username")
    String username;

    Message() {
    }

    public Message(@NonNull String id, @NonNull String conferenceId, @NonNull String userId,
                   @NonNull String message, @NonNull String action, long time,
                   @NonNull String username) {
        this.id = id;
        this.conferenceId = conferenceId;
        this.userId = userId;
        this.message = message;
        this.action = action;
        this.time = time;
        this.username = username;
    }

    protected Message(Parcel in) {
        this.id = in.readString();
        this.conferenceId = in.readString();
        this.userId = in.readString();
        this.message = in.readString();
        this.action = in.readString();
        this.time = in.readLong();
        this.username = in.readString();
    }

    @NonNull
    @Override
    public String getId() {
        return id;
    }

    @NonNull
    public String getConferenceId() {
        return conferenceId;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    @NonNull
    public String getMessage() {
        return message;
    }

    @NonNull
    public String getAction() {
        return action;
    }

    @Override
    public long getTime() {
        return time;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (time != message1.time) return false;
        if (!id.equals(message1.id)) return false;
        if (!conferenceId.equals(message1.conferenceId)) return false;
        if (!userId.equals(message1.userId)) return false;
        if (!message.equals(message1.message)) return false;
        if (!action.equals(message1.action)) return false;
        return username.equals(message1.username);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + conferenceId.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + action.hashCode();
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + username.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.conferenceId);
        dest.writeString(this.userId);
        dest.writeString(this.message);
        dest.writeString(this.action);
        dest.writeLong(this.time);
        dest.writeString(this.username);
    }
}
