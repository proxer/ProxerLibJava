package com.proxerme.library.info;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import okhttp3.HttpUrl;

public class ProxerUrlHolder {

    private static final String SCHEME = "https";
    private static final String API_HOST = "proxer.me";
    private static final String IMAGE_HOST = "cdn.proxer.me";

    private static final String API_SEGMENT = "api";
    private static final String VERSION_SEGMENT = "v1";

    private static final String NEWS_SEGMENT = "news";
    private static final String TEMPORARY_SEGMENT = "tmp";
    private static final String NEWS_IMAGE_SEGMENT = "%s_%s.png";

    private static final String AVATAR_SEGMENT = "avatar";

    private static final String COVER_SEGMENT = "cover";
    private static final String COVER_IMAGE_SEGMENT = "%s.jpg";

    private static final String FORUM_SEGMENT = "forum";

    private static final String DONATE_SEGMENT = "donate";

    private static final String DEVICE_QUERY_PARAMETER = "device";
    private static final String DEVICE_QUERY_PARAMETER_DEFAULT = "mobile";

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
                .addPathSegment(API_SEGMENT)
                .addPathSegment(VERSION_SEGMENT)
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
                .addPathSegment(NEWS_SEGMENT)
                .addPathSegment(TEMPORARY_SEGMENT)
                .addPathSegment(String.format(NEWS_IMAGE_SEGMENT, newsId, imageId))
                .build();
    }

    @NonNull
    public static HttpUrl getUserImageUrl(@NonNull String imageLink) {
        return getImageHost().newBuilder()
                .addPathSegment(AVATAR_SEGMENT)
                .addPathSegment(imageLink)
                .build();
    }

    @NonNull
    public static HttpUrl getCoverImageUrl(@NonNull String entryId) {
        return getImageHost().newBuilder()
                .addPathSegment(COVER_SEGMENT)
                .addPathSegment(String.format(COVER_IMAGE_SEGMENT, entryId))
                .build();
    }

    @NonNull
    public static HttpUrl getNewsUrl(@NonNull String categoryId, @NonNull String threadId,
                                     @Nullable String device) {
        return getBaseApiHost().newBuilder()
                .addPathSegment(FORUM_SEGMENT)
                .addPathSegment(categoryId)
                .addPathSegment(threadId)
                .addQueryParameter(DEVICE_QUERY_PARAMETER, buildDeviceQueryParameter(device))
                .build();
    }

    @NonNull
    public static HttpUrl getDonateUrl(@Nullable String device) {
        return getBaseApiHost().newBuilder()
                .addPathSegment(DONATE_SEGMENT)
                .addQueryParameter(DEVICE_QUERY_PARAMETER, buildDeviceQueryParameter(device))
                .build();
    }

    private static String buildDeviceQueryParameter(@Nullable String parameter) {
        return parameter == null ? DEVICE_QUERY_PARAMETER_DEFAULT : parameter;
    }

}
