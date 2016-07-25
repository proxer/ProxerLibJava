package com.proxerme.library.connection.experimental.chat.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.proxerme.library.interfaces.TimeItem;

/**
 * Entity representing a single message.
 *
 * @author Ruben Gees
 */
public class Message implements IdItem, ImageItem, TimeItem, Parcelable {

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

    @Body(name = "id")
    String id;
    @Body(name = "fromid")
    String fromId;
    @Body(name = "message")
    String message;

    @Body(name = "action")
    String action;

    @Body(name = "timestamp")
    long time;
    @Body(name = "device")
    String device;
    @Body(name = "username")
    String username;
    @Body(name = "avatar")
    String imageId;

    Message() {
    }

    /**
     * The constructor.
     *
     * @param id       The id of this message.
     * @param fromId   The id of the user, which sent this message.
     * @param message  The content of the message.
     * @param action   The action of the message. This might be something like "removeUser" in a
     *                 conference.
     * @param time     The time this message was sent as an unix timestamp.
     * @param device   The device this message was sent from.
     * @param username The username of the sender.
     * @param imageId  The id of the image of the User. This can be retrieved by using
     *                 {@link com.proxerme.library.info.ProxerUrlHolder#getUserImageUrl(String)}.
     */
    public Message(@NonNull String id, @NonNull String fromId, @NonNull String message,
                   @NonNull String action, long time, @NonNull String device,
                   @NonNull String username, @NonNull String imageId) {
        this.id = id;
        this.fromId = fromId;
        this.message = message;
        this.action = action;
        this.time = time;
        this.device = device;
        this.username = username;
        this.imageId = imageId;
    }

    protected Message(Parcel in) {
        this.id = in.readString();
        this.fromId = in.readString();
        this.message = in.readString();
        this.action = in.readString();
        this.time = in.readLong();
        this.device = in.readString();
        this.username = in.readString();
        this.imageId = in.readString();
    }

    /**
     * Returns the id of this message.
     *
     * @return The id.
     */
    @NonNull
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the id of the User.
     *
     * @return The id.
     */
    @NonNull
    public String getFromId() {
        return fromId;
    }

    /**
     * Returns the content of the message.
     *
     * @return The message.
     */
    @NonNull
    public String getMessage() {
        return message;
    }

    /**
     * Returns the action of this message. Might be blank.
     *
     * @return The action.
     */
    @NonNull
    public String getAction() {
        return action;
    }

    /**
     * Returns the time this message was sent.
     *
     * @return The time as an unix timestamp.
     */
    @Override
    public long getTime() {
        return time;
    }

    /**
     * Returns the devices this message was sent from.
     *
     * @return The device.
     */
    @NonNull
    public String getDevice() {
        return device;
    }

    /**
     * Returns the username of the sender.
     *
     * @return The username.
     */
    @NonNull
    public String getUsername() {
        return username;
    }

    /**
     * Returns the id of the image.
     *
     * @return The id.
     */
    @NonNull
    @Override
    public String getImageId() {
        return imageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (time != message1.time) return false;
        if (!id.equals(message1.id)) return false;
        if (!fromId.equals(message1.fromId)) return false;
        if (!message.equals(message1.message)) return false;
        if (!action.equals(message1.action)) return false;
        if (!device.equals(message1.device)) return false;
        if (!username.equals(message1.username)) return false;
        return imageId.equals(message1.imageId);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + fromId.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + action.hashCode();
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + device.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + imageId.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.fromId);
        dest.writeString(this.message);
        dest.writeValue(this.action);
        dest.writeLong(this.time);
        dest.writeString(this.device);
        dest.writeString(this.username);
        dest.writeString(this.imageId);
    }
}
