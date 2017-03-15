package com.proxerme.library.util;

import com.proxerme.library.enums.Device;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.UtilityClass;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;

import static com.proxerme.library.enums.Device.DEFAULT;

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
     * Returns the base url for all web pages.
     */
    @Getter(onMethod = @__({@NotNull}))
    private final HttpUrl webBase = new HttpUrl.Builder()
            .scheme("https")
            .host("proxer.me")
            .addPathSegment("")
            .build();

    /**
     * Returns the base url for all api calls.
     */
    @Getter(onMethod = @__({@NotNull}))
    private final HttpUrl apiBase = webBase.newBuilder()
            .addPathSegment("api")
            .addPathSegment("v1")
            .addPathSegment("")
            .build();

    /**
     * Returns the base url for all image links.
     */
    @Getter(onMethod = @__({@NotNull}))
    private final HttpUrl cdnBase = new HttpUrl.Builder()
            .scheme("https")
            .host("cdn.proxer.me")
            .addPathSegment("")
            .build();

    /**
     * Returns the image link of a {@link com.proxerme.library.entitiy.notifications.NewsArticle}, based on its
     * {@code id} and {@code image}.
     */
    @NotNull
    public HttpUrl newsImage(@NotNull final String id, @NotNull final String image) {
        return cdnBase.newBuilder()
                .addPathSegment("news")
                .addPathSegment("tmp")
                .addPathSegment(String.format("%s_%s.png", id, image))
                .addPathSegment("")
                .build();
    }

    /**
     * Returns the image link of the user.
     */
    @NotNull
    public HttpUrl userImage(@NotNull final String image) {
        return cdnBase.newBuilder()
                .addPathSegment("avatar")
                .addPathSegment(image)
                .addPathSegment("")
                .build();
    }

    /**
     * Returns the cover image link of the entry.
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
     * Returns the image link of the translator group.
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
     * Returns the image link of the industry.
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
     * Returns the image link of the hoster.
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
     * Returns the image link of the page, based on the {@code server} number, the {@code entryId}, the {@code id} of
     * the chapter and the file{@code name} of the page.
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
     * Returns the link for the donation web page.
     */
    @NotNull
    public HttpUrl donateWeb() {
        return webBase.newBuilder()
                .addPathSegment("donate")
                .addPathSegment("")
                .build();
    }

    /**
     * Returns the link for the donation web page.
     */
    @NotNull
    public HttpUrl donateWeb(@NotNull final Device device) {
        return donateWeb().newBuilder()
                .addQueryParameter("device", deviceToString(device))
                .build();
    }

    /**
     * Returns the link for the wiki web page, based on the {@code topic}.
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
     * Returns the link for the user's web page.
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
     * Returns the link for the user's web page.
     */
    @NotNull
    public HttpUrl userWeb(@NotNull final String id, @NotNull final Device device) {
        return userWeb(id).newBuilder()
                .addQueryParameter("device", deviceToString(device))
                .build();
    }

    /**
     * Returns the link for the web page of the requested forum thread, residing in the category, specified by the
     * {@code categoryId}.
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
     * Returns the link for the web page of the requested forum thread, residing in the category, specified by the
     * {@code categoryId}.
     */
    @NotNull
    public HttpUrl forumWeb(@NotNull final String categoryId, @NotNull final String threadId,
                            @NotNull final Device device) {
        return forumWeb(categoryId, threadId).newBuilder()
                .addQueryParameter("device", deviceToString(device))
                .build();
    }

    /**
     * Returns the link for the web page of the requested news article, residing in the category, specified by the
     * {@code categoryId}.
     */
    @NotNull
    public HttpUrl newsWeb(@NotNull final String categoryId, @NotNull final String threadId) {
        return forumWeb(categoryId, threadId);
    }

    /**
     * Returns the link for the web page of the requested news article, residing in the category, specified by the
     * {@code categoryId}.
     */
    @NotNull
    public HttpUrl newsWeb(@NotNull final String categoryId, @NotNull final String threadId,
                           @NotNull final Device device) {
        return forumWeb(categoryId, threadId, device);
    }

    /**
     * Returns the link for the web page to solve the captcha.
     */
    @NotNull
    public HttpUrl captchaWeb() {
        return webBase().newBuilder()
                .addPathSegment("misc")
                .addPathSegment("captcha")
                .addPathSegment("")
                .build();
    }

    /**
     * Returns the link for the web page to solve the captcha.
     */
    @NotNull
    public HttpUrl captchaWeb(@NotNull final Device device) {
        return captchaWeb().newBuilder()
                .addQueryParameter("device", device == DEFAULT ? "default" : "mobile")
                .build();
    }

    private String deviceToString(@NotNull final Device device) {
        switch (device) {
            case DEFAULT:
                return "default";
            case MOBILE:
                return "mobile";
            case UNSPECIFIED:
                return "";
            default:
                throw new IllegalArgumentException();
        }
    }
}
