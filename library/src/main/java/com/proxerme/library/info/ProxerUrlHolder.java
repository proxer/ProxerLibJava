package com.proxerme.library.info;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Helper class, containing various Urls for accessing the API.
 *
 * @author Ruben Gees
 */
public class ProxerUrlHolder {

    private static final String HOST = "https://proxer.me";
    private static final String NEWS = "/forum/%s/%s%s#top";
    private static final String DONATE = "/donate%s";
    private static final String NEWS_IMAGE = "https://cdn.proxer.me/news/tmp/%s_%s.png";
    private static final String USER_IMAGE = "https://cdn.proxer.me/avatar/%s";
    private static final String COVER_IMAGE = "https://cdn.proxer.me/cover/%s.jpg";

    /**
     * Returns the host of the API.
     *
     * @return The Url.
     */
    @NonNull
    public static String getHost() {
        return HOST;
    }

    /*
     * Returns the Url for a single image of a news.
     *
     * @param newsId  The id of the news.
     * @param imageId The id of the image.
     * @return The Url.
     */
    @NonNull
    public static String getNewsImageUrl(@NonNull String newsId, @NonNull String imageId) {
        return String.format(NEWS_IMAGE, newsId, imageId);
    }

    /**
     * Returns the url for a single newspage. This is not part of the REST-Api, but the link to the
     * webpage.
     *
     * @param categoryId The id of the category of the news.
     * @param threadId   The id of the thread
     * @param device     An optional query parameter to specify the device. This is useful if you
     *                   want to show the mobile site. In that case you could pass "mobile".
     * @return The Url.
     */
    @NonNull
    public static String getNewsUrl(@NonNull String categoryId, @NonNull String threadId,
                                    @Nullable String device) {
        return String.format(getHost() + NEWS, categoryId, threadId,
                device == null ? "?device=default" : "?device=" + device);
    }

    /**
     * Returns the Url to a single image of a user.
     *
     * @param imageLink The link to the image.
     * @return The Url
     */
    @NonNull
    public static String getUserImageUrl(@NonNull String imageLink) {
        return String.format(USER_IMAGE, imageLink);
    }

    /**
     * Returns the Url to the cover of an entry (AnimeInfos, AnimeInfo, ...).
     *
     * @param entryId The id of the entry.
     * @return The Url.
     */
    @NonNull
    public static String getCoverImageUrl(@NonNull String entryId) {
        return String.format(COVER_IMAGE, entryId);
    }

    /**
     * Returns the Url for the donation page.
     *
     * @return The url.
     */
    @NonNull
    public static String getDonateUrl(@Nullable String device) {
        return String.format(getHost() + DONATE,
                device == null ? "?device=default" : "?device=" + device);
    }

}
