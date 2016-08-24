package com.proxerme.library.connection.messenger.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.TimeItem;
import com.proxerme.library.parameters.ActionParameter.Action;
import com.squareup.moshi.Json;

/**
 * Entity that represents a single message.
 *
 * @author Ruben Gees
 */
public class Message implements IdItem, TimeItem, Parcelable {

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel source) {
            return new Message(source);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    @Json(name = "message_id")
    private String id;
    @Json(name = "conference_id")
    private String conferenceId;
    @Json(name = "user_id")
    private String userId;
    @Json(name = "username")
    private String username;
    @Json(name = "message")
    private String message;
    @Json(name = "action")
    private String action;
    @Json(name = "timestamp")
    private long time;
    @Json(name = "device")
    private String device;

    private Message() {
    }

    /**
     * The constructor.
     *
     * @param id           The id of this message.
     * @param conferenceId The id of the conferenceInfo this message belongs to.
     * @param userId       The id of the user, who sent this message.
     * @param username     The username of the user, who sent this message.
     * @param message      The contents of the message.
     * @param action       The action of this message. This might be something like "addUser".
     *                     Might be empty if there is no action.
     * @param time         The time this message was sent.
     * @param device       The device this message was sent from. In most cases "default".
     */
    public Message(@NonNull String id, @NonNull String conferenceId, @NonNull String userId,
                   @NonNull String username, @NonNull String message,
                   @NonNull @Action String action, long time, @NonNull String device) {
        this.id = id;
        this.conferenceId = conferenceId;
        this.userId = userId;
        this.username = username;
        this.message = message;
        this.action = action;
        this.time = time;
        this.device = device;
    }

    protected Message(Parcel in) {
        this.id = in.readString();
        this.conferenceId = in.readString();
        this.userId = in.readString();
        this.username = in.readString();
        this.message = in.readString();
        this.action = in.readString();
        this.time = in.readLong();
        this.device = in.readString();
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
     * Returns the id of the conference this message belongs to.
     *
     * @return The id.
     */
    @NonNull
    public String getConferenceId() {
        return conferenceId;
    }

    /**
     * Returns the id of the user.
     *
     * @return The id.
     */
    @NonNull
    public String getUserId() {
        return userId;
    }

    /**
     * Returns the username of the user.
     *
     * @return The username.
     */
    @NonNull
    public String getUsername() {
        return username;
    }

    /**
     * Returns the actual contents of this message.
     *
     * @return The contents of this message.
     */
    @NonNull
    public String getMessage() {
        return message;
    }

    /**
     * Returns the action of this message. Might be empty.
     *
     * @return The action.
     */
    @NonNull
    @Action
    public String getAction() {
        return action;
    }

    /**
     * Returns the time of this message.
     *
     * @return The time.
     */
    @Override
    public long getTime() {
        return time;
    }

    /**
     * Returns the device this message was sent from.
     *
     * @return The device.
     */
    @NonNull
    public String getDevice() {
        return device;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (time != message1.time) return false;
        if (!id.equals(message1.id)) return false;
        if (!conferenceId.equals(message1.conferenceId)) return false;
        if (!userId.equals(message1.userId)) return false;
        if (!username.equals(message1.username)) return false;
        if (!message.equals(message1.message)) return false;
        if (!action.equals(message1.action)) return false;
        return device.equals(message1.device);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + conferenceId.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + action.hashCode();
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + device.hashCode();
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
        dest.writeString(this.username);
        dest.writeString(this.message);
        dest.writeString(this.action);
        dest.writeLong(this.time);
        dest.writeString(this.device);
    }
}
