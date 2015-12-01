package com.proxerme.library.connection;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

/**
 * Helper class, containing all the different Urls for accessing the API.
 *
 * @author Ruben Gees
 */
public class UrlHolder {

    private static final String LOGOUT = "/login?format=json&action=logout";
    private static final String HOST = "http://proxer.me";
    private static final String NEWS = "/notifications?format=json&s=news&p=";
    private static final String NEWS_IMAGE = "http://cdn.proxer.me/news/tmp/";
    private static final String LOGIN = "/login?format=json&action=login";
    private static final String USER_IMAGE = "/images/comprofiler/";
    private static final String CONFERENCES = "/messages?format=json&json=conferences&p=";
    private static final String CONFERENCE = "/messages?id=";

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
     * @param page The page to retrieve news from
     * @return The url.
     */
    @NonNull
    public static String getNewsUrl(@IntRange(from = 1) int page) {
        return getHost() + NEWS + page;
    }

    /**
     * Returns the url for a single image of a news.
     * @param newsId The id of the news.
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
     * @param categoryId The id of the category of the news.
     * @param threadId The id of the thred.
     * @return The url.
     */
    @NonNull
    public static String getNewsPageUrl(@NonNull String categoryId, @NonNull String threadId) {
        return getHost() + "/forum/" + categoryId + "/" + threadId + "#top";
    }

    /**
     * Returns the url to a single image of a user.
     * @param imageLink The link to the image.
     * @return The url.
     */
    @NonNull
    public static String getUserImage(@NonNull String imageLink) {
        return getHost() + USER_IMAGE + imageLink;
    }

    /**
     * Returns the url for the donation page.
     * @return The url.
     */
    @NonNull
    public static String getDonateUrl() {
        return getHost() + "/donate";
    }

    /**
     * Returns the url for a login.
     * @return The url.
     */
    @NonNull
    public static String getLoginUrl() {
        return getHost() + LOGIN;
    }

    /**
     * Returns the url for a logout.
     * @return The url.
     */
    @NonNull
    public static String getLogoutUrl() {
        return getHost() + LOGOUT;
    }

    /**
     * Returns the url for retrieval of conferences.
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
    public static String getConferenceUrl(@NonNull String id) {
        return getHost() + CONFERENCE + "#top";
    }

}
