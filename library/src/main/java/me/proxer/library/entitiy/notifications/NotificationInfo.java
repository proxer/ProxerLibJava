package me.proxer.library.entitiy.notifications;

import lombok.Value;

/**
 * @author Ruben Gees
 */
@Value
public class NotificationInfo {

    /**
     * Returns the amount of messages.
     */
    private int messageAmount;

    /**
     * Returns the amount of friend requests.
     */
    private int friendRequests;

    /**
     * Returns the amount of news.
     */
    private int news;

    /**
     * Returns the amount of general notifications.
     */
    private int notifications;
}
