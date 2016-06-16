package com.proxerme.library.connection.experimental.messages.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.proxerme.library.interfaces.TimeItem;

/**
 * TODO: Describe Class
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
    @Body(name = "image_id")
    String imageId;

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

    @NonNull
    @Override
    public String getId() {
        return id;
    }

    @NonNull
    public String getFromId() {
        return fromId;
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
    public String getDevice() {
        return device;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

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
