package com.proxerme.library.info;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import okhttp3.HttpUrl;

/**
 * Class which holds various often needed urls.
 */
public final class ProxerUrlHolder {

    public static final String USER = "user";
    private static final String SCHEME = "https";
    private static final String API_HOST = "proxer.me";
    private static final String IMAGE_HOST = "cdn.proxer.me";

    private static final String API_SEGMENT = "api";
    private static final String VERSION_SEGMENT = "v1";

    private static final String NEWS_SEGMENT = "news";
    private static final String TEMPORARY_SEGMENT = "tmp";
    private static final String NEWS_IMAGE_SEGMENT = "%s_%s.png";

    private static final String AVATAR_SEGMENT = "avatar";

    private static final String USER_SEGMENT = "user";

    private static final String COVER_SEGMENT = "cover";
    private static final String COVER_IMAGE_SEGMENT = "%s.jpg";

    private static final String FORUM_SEGMENT = "forum";

    private static final String DONATE_SEGMENT = "donate";

    private static final String TOP_SEGMENT = "#top";

    private static final String DEVICE_QUERY_PARAMETER = "device";
    private static final String DEVICE_QUERY_PARAMETER_DEFAULT = "mobile";

    private ProxerUrlHolder() {
    }

    /**
     * Returns the host.
     *
     * @return The host.
     */
    @NonNull
    public static HttpUrl getBaseApiHost() {
        return new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(API_HOST)
                .build();
    }

    /**
     * Returns the host of the api with the version extension.
     *
     * @return The host.
     */
    @NonNull
    public static HttpUrl getApiHost() {
        return getBaseApiHost().newBuilder()
                .addPathSegment(API_SEGMENT)
                .addPathSegment(VERSION_SEGMENT)
                .build();
    }

    /**
     * Returns the host of the image network.
     *
     * @return The host.
     */
    @NonNull
    public static HttpUrl getImageHost() {
        return new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(IMAGE_HOST)
                .build();
    }

    /**
     * Returns the url for the image of a
     * {@link com.proxerme.library.connection.notifications.entitiy.News}.
     *
     * @param newsId  The id of the News.
     * @param imageId The id of the image.
     * @return The url.
     */
    @NonNull
    public static HttpUrl getNewsImageUrl(@NonNull String newsId, @NonNull String imageId) {
        return getImageHost().newBuilder()
                .addPathSegment(NEWS_SEGMENT)
                .addPathSegment(TEMPORARY_SEGMENT)
                .addPathSegment(String.format(NEWS_IMAGE_SEGMENT, newsId, imageId))
                .build();
    }

    /**
     * Returns the url for the image of a {@link com.proxerme.library.connection.user.entitiy.User}.
     *
     * @param imageLink The link.
     * @return The url.
     */
    @NonNull
    public static HttpUrl getUserImageUrl(@NonNull String imageLink) {
        return getImageHost().newBuilder()
                .addPathSegment(AVATAR_SEGMENT)
                .addPathSegment(imageLink)
                .build();
    }

    /**
     * Returns the url to the user profile.
     *
     * @param id     The user id.
     * @param device Optional parameter to specify the device. Possible are "mobile" and
     *               "default. "mobile" is the default value.
     * @return The url.
     */
    @NonNull
    public static HttpUrl getUserUrl(@NonNull String id, @Nullable String device) {
        return getBaseApiHost().newBuilder()
                .addPathSegment(USER_SEGMENT)
                .addPathSegment(id)
                .addPathSegment(TOP_SEGMENT)
                .addQueryParameter(DEVICE_QUERY_PARAMETER, buildDeviceQueryParameter(device))
                .build();
    }

    /**
     * Returns the url for the cover image of a media entry.
     *
     * @param entryId The id of the entry.
     * @return The url.
     */
    @NonNull
    public static HttpUrl getCoverImageUrl(@NonNull String entryId) {
        return getImageHost().newBuilder()
                .addPathSegment(COVER_SEGMENT)
                .addPathSegment(String.format(COVER_IMAGE_SEGMENT, entryId))
                .build();
    }

    /**
     * Returns the url for the forum thread of a
     * {@link com.proxerme.library.connection.notifications.entitiy.News}.
     *
     * @param categoryId The id of the category.
     * @param threadId   The id of the thread.
     * @param device     Optional parameter to specify the device. Possible are "mobile" and
     *                   "default. "mobile" is the default value.
     * @return The url.
     */
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

    /**
     * Returns the url for the donate page.
     *
     * @param device Optional parameter to specify the device. Possible are "mobile" and
     *               "default. "mobile" is the default value.
     * @return The url.
     */
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
