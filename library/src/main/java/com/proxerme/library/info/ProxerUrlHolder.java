package com.proxerme.library.info;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import okhttp3.HttpUrl;

/**
 * Class which holds various often needed urls.
 */
public final class ProxerUrlHolder {

    public static final String DEVICE_QUERY_PARAMETER_DEFAULT = "default";
    public static final String DEVICE_QUERY_PARAMETER_MOBILE = "mobile";

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

    private static final String WIKI_SEGMENT = "wiki";

    private static final String TRANSLATOR_GROUP_SEGMENT = "translatorgroups";

    private static final String INDUSTRY_SEGMENT = "industry";

    private static final String ID_PARAMETER = "id";

    private static final String IMAGES_SEGMENT = "images";
    private static final String HOSTER_SEGMENT = "hoster";

    private static final String F_SEGMENT = "f";
    private static final String MANGA_SEGMENT = "manga";
    private static final String MANGA_SERVER_SEGMENT = ".proxer.me";

    private static final String DEVICE_QUERY_PARAMETER = "device";

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
     *               "default".
     * @return The url.
     */
    @NonNull
    public static HttpUrl getUserUrl(@NonNull String id, @Nullable @Device String device) {
        HttpUrl.Builder builder = getBaseApiHost().newBuilder()
                .addPathSegment(USER_SEGMENT)
                .addPathSegment(id);

        if (device != null) {
            builder.addQueryParameter(DEVICE_QUERY_PARAMETER, device);
        }

        return builder.build();
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
                .addPathSegment(TEMPORARY_SEGMENT)
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
     *                   "default".
     * @return The url.
     */
    @NonNull
    public static HttpUrl getNewsUrl(@NonNull String categoryId, @NonNull String threadId,
                                     @Nullable @Device String device) {
        HttpUrl.Builder builder = getBaseApiHost().newBuilder()
                .addPathSegment(FORUM_SEGMENT)
                .addPathSegment(categoryId)
                .addPathSegment(threadId);

        if (device != null) {
            builder.addQueryParameter(DEVICE_QUERY_PARAMETER, device);
        }

        return builder.build();
    }

    /**
     * Returns the url for the donate page.
     *
     * @param device Optional parameter to specify the device. Possible are "mobile" and
     *               "default".
     * @return The url.
     */
    @NonNull
    public static HttpUrl getDonateUrl(@Nullable @Device String device) {
        HttpUrl.Builder builder = getBaseApiHost().newBuilder()
                .addPathSegment(DONATE_SEGMENT);

        if (device != null) {
            builder.addQueryParameter(DEVICE_QUERY_PARAMETER, device);
        }

        return builder.build();
    }

    /**
     * Returns the url for a wiki page.
     *
     * @param topic The topic of the page.
     * @return The url.
     */
    @NonNull
    public static HttpUrl getWikiUrl(@NonNull String topic) {
        return getBaseApiHost().newBuilder()
                .addPathSegment(WIKI_SEGMENT)
                .addPathSegment(topic)
                .build();
    }

    /**
     * Returns the url for a info translator group page.
     *
     * @param id     The id of the translator group.
     * @param device Optional parameter to specify the device. Possible are "mobile" and
     *               "default".
     * @return The url.
     */
    @NonNull
    public static HttpUrl getTranslatorGroupUrl(@NonNull String id, @Nullable @Device String device) {
        HttpUrl.Builder builder = getBaseApiHost().newBuilder()
                .addPathSegment(TRANSLATOR_GROUP_SEGMENT)
                .addQueryParameter(ID_PARAMETER, id);

        if (device != null) {
            builder.addQueryParameter(DEVICE_QUERY_PARAMETER, device);
        }

        return builder.build();
    }

    /**
     * Returns the url for a industry info page.
     *
     * @param id     The id of the industry.
     * @param device Optional parameter to specify the device. Possible are "mobile" and
     *               "default".
     * @return The url.
     */
    @NonNull
    public static HttpUrl getPublisherUrl(@NonNull String id, @Nullable @Device String device) {
        HttpUrl.Builder builder = getBaseApiHost().newBuilder()
                .addPathSegment(INDUSTRY_SEGMENT)
                .addQueryParameter(ID_PARAMETER, id);

        if (device != null) {
            builder.addQueryParameter(DEVICE_QUERY_PARAMETER, device);
        }

        return builder.build();
    }

    /**
     * Returns the url for a hoster image.
     *
     * @param imageId The image id.
     * @return The url.
     */
    @NonNull
    public static HttpUrl getHosterImageUrl(@NonNull String imageId) {
        return getBaseApiHost().newBuilder()
                .addPathSegment(IMAGES_SEGMENT)
                .addPathSegment(HOSTER_SEGMENT)
                .addPathSegment(imageId)
                .build();
    }

    /**
     * Returns the url for a single page of a manga.
     *
     * @param server  The server.
     * @param entryId The entry id.
     * @param id      The id of the chapter.
     * @param name    The filename of the page.
     * @return The url.
     */
    @NonNull
    public static HttpUrl getMangaPageUrl(@NonNull String server, @NonNull String entryId,
                                          @NonNull String id, @NonNull String name) {
        return new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(MANGA_SEGMENT + server + MANGA_SERVER_SEGMENT)
                .addPathSegment(F_SEGMENT)
                .addPathSegment(entryId)
                .addPathSegment(id)
                .addPathSegment(name)
                .build();
    }

    /**
     * An annotation representing the available tag rate filters.
     */
    @StringDef({DEVICE_QUERY_PARAMETER_DEFAULT, DEVICE_QUERY_PARAMETER_MOBILE})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface Device {
    }
}
