package com.proxerme.library.connection;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

/**
 * Helper class, containing all the different Urls for accessing the API.
 *
 * @author Ruben Gees
 */
public class UrlHolder {

    private static final String HOST = "http://proxer.me";
    private static final String NEWS = "/notifications?format=json&s=news&p=";
    private static final String NEWS_IMAGE = "http://cdn.proxer.me/news/";
    private static final String LOGIN = "/login?format=json&action=login";

    @NonNull
    public static String getHost() {
        return HOST;
    }

    @NonNull
    public static String getNewsUrl(@IntRange(from = 1) int page) {
        return getHost() + NEWS + page;
    }

    @NonNull
    public static String getNewsImageUrl(@NonNull String newsId, @NonNull String imageId) {
        return NEWS_IMAGE + newsId + "_" + imageId + ".png";
    }

    @NonNull
    public static String getNewsPageUrl(@NonNull String categoryId, @NonNull String threadId) {
        return getHost() + "/forum/" + categoryId + "/" + threadId + "#top";
    }

    @NonNull
    public static String getDonateUrl() {
        return getHost() + "/donate";
    }

    @NonNull
    public static String getLoginUrl() {
        return getHost() + LOGIN;
    }

}
