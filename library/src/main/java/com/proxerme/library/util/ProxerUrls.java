package com.proxerme.library.util;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.UtilityClass;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;

import static com.proxerme.library.util.ProxerUrls.Device.DEFAULT;

/**
 * Utility class with various static methods for retrieving web and image links.
 * Moreover it holds the base urls of web, api and image cdn.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@UtilityClass
@Accessors(fluent = true)
public final class ProxerUrls {

    /**
     * @return The base url for all web pages.
     */
    @Getter(onMethod = @__({@NotNull}))
    private final HttpUrl webBase = new HttpUrl.Builder()
            .scheme("https")
            .host("proxer.me")
            .addPathSegment("")
            .build();

    /**
     * @return The base url for all api calls.
     */
    @Getter(onMethod = @__({@NotNull}))
    private final HttpUrl apiBase = webBase.newBuilder()
            .addPathSegment("api")
            .addPathSegment("v1")
            .addPathSegment("")
            .build();

    /**
     * @return The base url for all image links.
     */
    @Getter(onMethod = @__({@NotNull}))
    private final HttpUrl cdnBase = new HttpUrl.Builder()
            .scheme("https")
            .host("cdn.proxer.me")
            .addPathSegment("")
            .build();

    /**
     * @param id      The id.
     * @param imageId The image id.
     * @return The image link of a {@link com.proxerme.library.entitiy.notifications.NewsArticle}.
     */
    @NotNull
    public HttpUrl newsImage(@NotNull final String id, @NotNull final String imageId) {
        return cdnBase.newBuilder()
                .addPathSegment("news")
                .addPathSegment("tmp")
                .addPathSegment(String.format("%s_%s.png", id, imageId))
                .addPathSegment("")
                .build();
    }

    /**
     * @param imageId The id.
     * @return The image link of the user.
     */
    @NotNull
    public HttpUrl userImage(@NotNull final String imageId) {
        return cdnBase.newBuilder()
                .addPathSegment("avatar")
                .addPathSegment(imageId)
                .addPathSegment("")
                .build();
    }

    /**
     * @param id The id.
     * @return The cover image link of an entry.
     */
    @NotNull
    public HttpUrl entryImage(@NotNull final String id) {
        return cdnBase.newBuilder()
                .addPathSegment("cover")
                .addPathSegment("tmp")
                .addPathSegment(String.format("%s.jpg", id))
                .addPathSegment("")
                .build();
    }

    /**
     * @param id The id.
     * @return The image link of the translator group.
     */
    @NotNull
    public HttpUrl translatorGroupImage(@NotNull final String id) {
        return cdnBase.newBuilder()
                .addPathSegment("translatorgroups")
                .addPathSegment(String.format("%s.jpg", id))
                .addPathSegment("")
                .build();
    }

    /**
     * @param id The id.
     * @return The image link of the industry.
     */
    @NotNull
    public HttpUrl industryImage(@NotNull final String id) {
        return cdnBase.newBuilder()
                .addPathSegment("industry")
                .addPathSegment(String.format("%s.jpg", id))
                .addPathSegment("")
                .build();
    }

    /**
     * @param id The id.
     * @return The image link of the hoster.
     */
    @NotNull
    public static HttpUrl hosterImage(@NotNull final String id) {
        return cdnBase.newBuilder()
                .addPathSegment("images")
                .addPathSegment("hoster")
                .addPathSegment(id)
                .addPathSegment("")
                .build();
    }

    /**
     * @param server  The server number, the chapter resides on.
     * @param entryId The id of the associated {@link com.proxerme.library.entitiy.info.Entry}.
     * @param id      The id of the chapter.
     * @param name    The filename of the page.
     * @return The image link of the page.
     */
    @NotNull
    public static HttpUrl mangaPageImage(@NotNull final String server, @NotNull final String entryId,
                                         @NotNull final String id, @NotNull final String name) {
        return new HttpUrl.Builder()
                .scheme("https")
                .host(String.format("manga%s.proxer.me", server))
                .addPathSegment("f")
                .addPathSegment(entryId)
                .addPathSegment(id)
                .addPathSegment(name)
                .addPathSegment("")
                .build();
    }

    /**
     * @return The link for the donation web page.
     */
    @NotNull
    public HttpUrl donateWeb() {
        return webBase.newBuilder()
                .addPathSegment("donate")
                .addPathSegment("")
                .build();
    }

    /**
     * @param device The device in use.
     * @return The link for the donation web page.
     */
    @NotNull
    public HttpUrl donateWeb(@NotNull final Device device) {
        return donateWeb().newBuilder()
                .addQueryParameter("device", device == DEFAULT ? "default" : "mobile")
                .build();
    }

    /**
     * @param topic The topic of the article.
     * @return The link for the wiki web page.
     */
    @NotNull
    public static HttpUrl wikiWeb(@NotNull final String topic) {
        return webBase.newBuilder()
                .addPathSegment("wiki")
                .addPathSegment(topic)
                .addPathSegment("")
                .build();
    }

    /**
     * @param id The id of the user.
     * @return The link for the user web page.
     */
    @NotNull
    public HttpUrl userWeb(@NotNull final String id) {
        return webBase.newBuilder()
                .addPathSegment("user")
                .addPathSegment(id)
                .addPathSegment("")
                .build();
    }

    /**
     * @param id     The id of the user.
     * @param device The device in use.
     * @return The link for the user web page.
     */
    @NotNull
    public HttpUrl userWeb(@NotNull final String id, @NotNull final Device device) {
        return userWeb(id).newBuilder()
                .addQueryParameter("device", device == DEFAULT ? "default" : "mobile")
                .build();
    }

    /**
     * @param categoryId The id of the category.
     * @param threadId   The id of the thread.
     * @return The link for the web page of the requested forum thread.
     */
    @NotNull
    public HttpUrl forumWeb(@NotNull final String categoryId, @NotNull final String threadId) {
        return webBase.newBuilder()
                .addPathSegment("forum")
                .addPathSegment(categoryId)
                .addPathSegment(threadId)
                .addPathSegment("")
                .build();
    }

    /**
     * @param categoryId The id of the category.
     * @param threadId   The id of the thread.
     * @param device     The device in use.
     * @return The link for the web page of the requested forum thread.
     */
    @NotNull
    public HttpUrl forumWeb(@NotNull final String categoryId, @NotNull final String threadId,
                            @NotNull final Device device) {
        return forumWeb(categoryId, threadId).newBuilder()
                .addQueryParameter("device", device == DEFAULT ? "default" : "mobile")
                .build();
    }

    /**
     * @param categoryId The id of the category.
     * @param threadId   The id of the thread.
     * @return The link for the web page of the requested news article.
     */
    @NotNull
    public HttpUrl newsWeb(@NotNull final String categoryId, @NotNull final String threadId) {
        return forumWeb(categoryId, threadId);
    }

    /**
     * @param categoryId The id of the category.
     * @param threadId   The id of the thread.
     * @param device     The device in use.
     * @return The link for the web page of the requested news article.
     */
    @NotNull
    public HttpUrl newsWeb(@NotNull final String categoryId, @NotNull final String threadId,
                           @NotNull final Device device) {
        return forumWeb(categoryId, threadId, device);
    }

    /**
     * Simple enum for the available types of devices, supported by Proxer.
     * Each leads to a different theme and is cached with a cookie. If you want to change, you have to call one of the
     * methods with a "device" parameter.
     */
    public enum Device {
        DEFAULT,
        MOBILE
    }
}
