package me.proxer.library.entitiy.notifications;

import lombok.Getter;
import lombok.Value;

/**
 * @author Ruben Gees
 */
@Value
public class NotificationInfo {

    /**
     * Returns the amount of messages.
     */
    @Getter
    private int messageAmount;

    /**
     * Returns the amount of friend requests.
     */
    @Getter
    private int friendRequests;

    /**
     * Returns the amount of news.
     */
    @Getter
    private int news;

    /**
     * Returns the amount of general notifications.
     */
    @Getter
    private int notifications;
}
