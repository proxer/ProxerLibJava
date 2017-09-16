package me.proxer.library.entity.notifications;

import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.annotation.Nullable;

/**
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
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
