package com.proxerme.library.info;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import okhttp3.HttpUrl;

public class ProxerUrlHolder {

    private static final String SCHEME = "https";
    private static final String API_HOST = "proxer.me";
    private static final String IMAGE_HOST = "cdn.proxer.me";

    private static final String HOST = "https://proxer.me";
    private static final String NEWS = "/forum/%s/%s%s#top";
    private static final String DONATE = "/donate%s";
    private static final String NEWS_IMAGE = "https://cdn.proxer.me/news/tmp/%s_%s.png";
    private static final String USER_IMAGE = "https://cdn.proxer.me/avatar/%s";
    private static final String COVER_IMAGE = "https://cdn.proxer.me/cover/%s.jpg";

    @NonNull
    public static HttpUrl getBaseApiHost() {
        return new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(API_HOST)
                .build();
    }

    @NonNull
    public static HttpUrl getApiHost() {
        return getBaseApiHost().newBuilder()
                .addPathSegment("api")
                .addPathSegment("v1")
                .build();
    }

    @NonNull
    public static HttpUrl getImageHost() {
        return new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(IMAGE_HOST)
                .build();
    }

    @NonNull
    public static HttpUrl getNewsImageUrl(@NonNull String newsId, @NonNull String imageId) {
        return getImageHost().newBuilder()
                .addPathSegment("news")
                .addPathSegment("tmp")
                .addPathSegment(String.format("%s_%s.png", newsId, imageId))
                .build();
    }

    @NonNull
    public static HttpUrl getUserImageUrl(@NonNull String imageLink) {
        return getImageHost().newBuilder()
                .addPathSegment("avatar")
                .addPathSegment(imageLink)
                .build();
    }

    @NonNull
    public static HttpUrl getCoverImageUrl(@NonNull String entryId) {
        return getImageHost().newBuilder()
                .addPathSegment("cover")
                .addPathSegment(String.format("%s.jpg", entryId))
                .build();
    }

    @NonNull
    public static HttpUrl getNewsUrl(@NonNull String categoryId, @NonNull String threadId,
                                     @Nullable String device) {
        return getBaseApiHost().newBuilder()
                .addPathSegment("forum")
                .addPathSegment(categoryId)
                .addPathSegment(threadId)
                .addQueryParameter("device", buildDeviceQueryParameter(device))
                .build();
    }

    @NonNull
    public static HttpUrl getDonateUrl(@Nullable String device) {
        return getBaseApiHost().newBuilder()
                .addPathSegment("donate")
                .addQueryParameter("device", buildDeviceQueryParameter(device))
                .build();
    }

    private static String buildDeviceQueryParameter(@Nullable String parameter) {
        return parameter == null ? "mobile" : parameter;
    }

}
