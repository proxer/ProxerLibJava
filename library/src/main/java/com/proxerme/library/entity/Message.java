package com.proxerme.library.entity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class Message {

    private String id;
    private String fromId;
    private String message;

    @Nullable
    private Integer action;

    private long time;
    private String device;
    private String username;

    public Message(@NonNull String id, @NonNull String fromId, @NonNull String message,
                   @Nullable String action, long time, @NonNull String device,
                   @NonNull String username) {
        this.id = id;
        this.fromId = fromId;
        this.message = message;
        this.action = getActionForString(action);
        this.time = time;
        this.device = device;
        this.username = username;
    }

    @NonNull
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

    @Nullable
    public Integer getAction() {
        return action;
    }

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

    @Nullable
    private Integer getActionForString(@Nullable String action) {
        if (action == null) {
            return null;
        }

        if (action.equals("addUser")) {
            return ConferenceAction.ADD_USER;
        }

        return null;
    }

    public static class ConferenceAction {
        public static int ADD_USER = 0;
    }
}
