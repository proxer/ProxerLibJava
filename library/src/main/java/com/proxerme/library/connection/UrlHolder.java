package com.proxerme.library.connection;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.proxerme.library.entity.Conference;

/**
 * Helper class, containing all the different Urls for accessing the API.
 *
 * @author Ruben Gees
 */
public class UrlHolder {

    private static final String LOGOUT = "/login?format=json&action=logout";
    private static final String HOST = "https://proxer.me";
    private static final String NEWS = "/notifications?format=json&s=news&p=";
    private static final String NEWS_IMAGE = "http://cdn.proxer.me/news/tmp/";
    private static final String LOGIN = "/login?format=json&action=login";
    private static final String USER_IMAGE = "http://cdn.proxer.me/avatar/";
    private static final String CONFERENCES = "/messages?format=json&json=conferences&p=";
    private static final String CONFERENCE = "/messages?id=";
    private static final String MESSAGES = "/messages?format=json&json=messages&id=";
    private static final String SEND_MESSAGE = "/messages/?format=json&json=answer&id=";

    /**
     * Returns the host of the API.
     *
     * @return The url.
     */
    @NonNull
    public static String getHost() {
        return HOST;
    }

    /**
     * Returns the url for news retrieval.
     *
     * @param page The page to retrieve news from
     * @return The url.
     */
    @NonNull
    public static String getNewsUrl(@IntRange(from = 1) int page) {
        return getHost() + NEWS + page;
    }

    /**
     * Returns the url for a single image of a news.
     *
     * @param newsId  The id of the news.
     * @param imageId The id of the image.
     * @return The url.
     */
    @NonNull
    public static String getNewsImageUrl(@NonNull String newsId, @NonNull String imageId) {
        return NEWS_IMAGE + newsId + "_" + imageId + ".png";
    }

    /**
     * Returns the url to a single newspage. This is not part of the REST-Api, but the link to the
     * webpage.
     *
     * @param categoryId The id of the category of the news.
     * @param threadId   The id of the thred.
     * @return The url.
     */
    @NonNull
    public static String getSingleNewsUrlWeb(@NonNull String categoryId, @NonNull String threadId) {
        return getHost() + "/forum/" + categoryId + "/" + threadId + "#top";
    }

    /**
     * Returns the url to a single image of a user.
     *
     * @param imageLink The link to the image.
     * @return The url.
     */
    @NonNull
    public static String getUserImageUrl(@NonNull String imageLink) {
        return USER_IMAGE + imageLink;
    }

    /**
     * Returns the url for the donation page.
     *
     * @return The url.
     */
    @NonNull
    public static String getDonateUrl() {
        return getHost() + "/donate";
    }

    /**
     * Returns the url for a login.
     *
     * @return The url.
     */
    @NonNull
    public static String getLoginUrl() {
        return getHost() + LOGIN;
    }

    /**
     * Returns the url for a logout.
     *
     * @return The url.
     */
    @NonNull
    public static String getLogoutUrl() {
        return getHost() + LOGOUT;
    }

    /**
     * Returns the url for retrieval of conferences.
     *
     * @param page The page to retrieve conferences from.
     * @return The url.
     */
    @NonNull
    public static String getConferencesUrl(@IntRange(from = 1) int page) {
        return getHost() + CONFERENCES + page;
    }

    /**
     * Returns the url to a single conference. This is not part of the REST-Api, but the link to the
     * webpage.
     *
     * @param id The id.
     * @return The url.
     */
    @NonNull
    public static String getConferenceUrlWeb(@NonNull String id) {
        return getHost() + CONFERENCE + id + "#top";
    }

    /**
     * Returns the url for retrieval of messages in a single conference.
     * Note that the Api is not officially released for this, so while being unlikely, the Api might
     * change.
     *
     * @param conferenceId The id of the {@link Conference}.
     * @param page         The page to retrieve conferences from. The first page of this Api is 0
     *                     and not 1. This might be an error of the Api.
     * @return The url.
     */
    @NonNull
    public static String getMessagesUrl(@NonNull String conferenceId, @IntRange(from = 0) int page) {
        return getHost() + MESSAGES + conferenceId + "&p=" + page;
    }

    /**
     * Returns the url for sending a single message. The message itself is send using POST.
     * Note that this Api is not official and might change at any time.
     *
     * @param conferenceId The id of the {@link Conference}.
     * @return The url.
     */
    @NonNull
    public static String getSendMessageUrl(@NonNull String conferenceId) {
        return getHost() + SEND_MESSAGE + conferenceId;
    }

}
