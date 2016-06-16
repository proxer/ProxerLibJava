package com.proxerme.library.info;

import android.support.annotation.NonNull;

/**
 * Helper class, containing all the different Urls for accessing the API.
 *
 * @author Ruben Gees
 */
public class ProxerUrlHolder {

    private static final String HOST = "https://proxer.me";
    private static final String NEWS = "/forum/%s/%s#top";
    private static final String DONATE = "/donate";
    private static final String NEWS_IMAGE = "http://cdn.proxer.me/news/tmp/%s_%s.png";
    private static final String USER_IMAGE = "http://cdn.proxer.me/avatar/%s";

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
     * Returns the url for a single image of a news.
     *
     * @param newsId  The id of the news.
     * @param imageId The id of the image.
     * @return The url.
     */
    @NonNull
    public static String getNewsImageUrl(@NonNull String newsId, @NonNull String imageId) {
        return String.format(NEWS_IMAGE, newsId, imageId);
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
    public static String getNewsUrl(@NonNull String categoryId, @NonNull String threadId) {
        return String.format(getHost() + NEWS, categoryId, threadId);
    }

    /**
     * Returns the url to a single image of a user.
     *
     * @param imageLink The link to the image.
     * @return The url.
     */
    @NonNull
    public static String getUserImageUrl(@NonNull String imageLink) {
        return String.format(USER_IMAGE, imageLink);
    }

    /**
     * Returns the url for the donation page.
     *
     * @return The url.
     */
    @NonNull
    public static String getDonateUrl() {
        return getHost() + DONATE;
    }


}
