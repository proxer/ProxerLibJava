package me.proxer.library.util;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.UtilityClass;
import me.proxer.library.entitiy.notifications.NewsArticle;
import me.proxer.library.enums.Device;
import okhttp3.HttpUrl;

import javax.annotation.Nonnull;
import java.util.regex.Pattern;

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

    private static final Pattern proxerHostPattern = Pattern.compile("(manga[0-9]+|(s[0-9]+\\.)?stream)\\.proxer\\.me");

    /**
     * Returns the base url for all web pages.
     */
    @Getter(onMethod = @__({@Nonnull}))
    private final HttpUrl webBase = new HttpUrl.Builder()
            .scheme("https")
            .host("proxer.me")
            .addPathSegment("")
            .build();

    /**
     * Returns the base url for all api calls.
     */
    @Getter(onMethod = @__({@Nonnull}))
    private final HttpUrl apiBase = webBase.newBuilder()
            .addPathSegment("api")
            .addPathSegment("v1")
            .addPathSegment("")
            .build();

    /**
     * Returns the base url for all image links.
     */
    @Getter(onMethod = @__({@Nonnull}))
    private final HttpUrl cdnBase = new HttpUrl.Builder()
            .scheme("https")
            .host("cdn.proxer.me")
            .addPathSegment("")
            .build();

    /**
     * Returns the image link of a {@link NewsArticle}, based on its
     * {@code id} and {@code image}.
     */
    @Nonnull
    public HttpUrl newsImage(@Nonnull final String id, @Nonnull final String image) {
        return cdnBase.newBuilder()
                .addPathSegment("news")
                .addPathSegment("tmp")
                .addPathSegment(String.format("%s_%s.png", id, image))
                .build();
    }

    /**
     * Returns the image link of the user.
     */
    @Nonnull
    public HttpUrl userImage(@Nonnull final String image) {
        return cdnBase.newBuilder()
                .addPathSegment("avatar")
                .addPathSegment(image)
                .build();
    }

    /**
     * Returns the cover image link of the entry.
     */
    @Nonnull
    public HttpUrl entryImage(@Nonnull final String id) {
        return cdnBase.newBuilder()
                .addPathSegment("cover")
                .addPathSegment("tmp")
                .addPathSegment(String.format("%s.jpg", id))
                .build();
    }

    /**
     * Returns the image link of the translator group.
     */
    @Nonnull
    public HttpUrl translatorGroupImage(@Nonnull final String id) {
        return cdnBase.newBuilder()
                .addPathSegment("translatorgroups")
                .addPathSegment(String.format("%s.jpg", id))
                .build();
    }

    /**
     * Returns the image link of the industry.
     */
    @Nonnull
    public HttpUrl industryImage(@Nonnull final String id) {
        return cdnBase.newBuilder()
                .addPathSegment("industry")
                .addPathSegment(String.format("%s.jpg", id))
                .build();
    }

    /**
     * Returns the image link of the hoster.
     */
    @Nonnull
    public static HttpUrl hosterImage(@Nonnull final String id) {
        return webBase.newBuilder()
                .addPathSegment("images")
                .addPathSegment("hoster")
                .addPathSegment(id)
                .build();
    }

    /**
     * Returns the image link of the page, based on the {@code server} number, the {@code entryId}, the {@code id} of
     * the chapter and the file{@code name} of the page.
     */
    @Nonnull
    public static HttpUrl mangaPageImage(@Nonnull final String server, @Nonnull final String entryId,
                                         @Nonnull final String id, @Nonnull final String name) {
        return new HttpUrl.Builder()
                .scheme("https")
                .host(String.format("manga%s.proxer.me", server))
                .addPathSegment("f")
                .addPathSegment(entryId)
                .addPathSegment(id)
                .addPathSegment(name)
                .build();
    }

    /**
     * Returns the link for the donation web page.
     */
    @Nonnull
    public HttpUrl donateWeb() {
        return webBase.newBuilder()
                .addPathSegment("donate")
                .build();
    }

    /**
     * Returns the link for the donation web page.
     */
    @Nonnull
    public HttpUrl donateWeb(@Nonnull final Device device) {
        return donateWeb().newBuilder()
                .addQueryParameter("device", ProxerUtils.getApiEnumName(device))
                .build();
    }

    /**
     * Returns the link for the wiki web page, based on the {@code topic}.
     */
    @Nonnull
    public static HttpUrl wikiWeb(@Nonnull final String topic) {
        return webBase.newBuilder()
                .addPathSegment("wiki")
                .addPathSegment(topic)
                .build();
    }

    /**
     * Returns the link for the user's web page.
     */
    @Nonnull
    public HttpUrl userWeb(@Nonnull final String id) {
        return webBase.newBuilder()
                .addPathSegment("user")
                .addPathSegment(id)
                .build();
    }

    /**
     * Returns the link for the user's web page.
     */
    @Nonnull
    public HttpUrl userWeb(@Nonnull final String id, @Nonnull final Device device) {
        return userWeb(id).newBuilder()
                .addQueryParameter("device", ProxerUtils.getApiEnumName(device))
                .build();
    }

    /**
     * Returns the link for the web page of the requested forum thread, residing in the category, specified by the
     * {@code categoryId}.
     */
    @Nonnull
    public HttpUrl forumWeb(@Nonnull final String categoryId, @Nonnull final String threadId) {
        return webBase.newBuilder()
                .addPathSegment("forum")
                .addPathSegment(categoryId)
                .addPathSegment(threadId)
                .build();
    }

    /**
     * Returns the link for the web page of the requested forum thread, residing in the category, specified by the
     * {@code categoryId}.
     */
    @Nonnull
    public HttpUrl forumWeb(@Nonnull final String categoryId, @Nonnull final String threadId,
                            @Nonnull final Device device) {
        return forumWeb(categoryId, threadId).newBuilder()
                .addQueryParameter("device", ProxerUtils.getApiEnumName(device))
                .build();
    }

    /**
     * Returns the link for the web page of the requested news article, residing in the category, specified by the
     * {@code categoryId}.
     */
    @Nonnull
    public HttpUrl newsWeb(@Nonnull final String categoryId, @Nonnull final String threadId) {
        return forumWeb(categoryId, threadId);
    }

    /**
     * Returns the link for the web page of the requested news article, residing in the category, specified by the
     * {@code categoryId}.
     */
    @Nonnull
    public HttpUrl newsWeb(@Nonnull final String categoryId, @Nonnull final String threadId,
                           @Nonnull final Device device) {
        return forumWeb(categoryId, threadId, device);
    }

    /**
     * Returns the link for the web page to solve the captcha.
     */
    @Nonnull
    public HttpUrl captchaWeb() {
        return webBase().newBuilder()
                .addPathSegment("misc")
                .addPathSegment("captcha")
                .build();
    }

    /**
     * Returns the link for the web page to solve the captcha.
     */
    @Nonnull
    public HttpUrl captchaWeb(@Nonnull final Device device) {
        return captchaWeb().newBuilder()
                .addQueryParameter("device", device == Device.DEFAULT ? "default" : "mobile")
                .build();
    }

    /**
     * Returns if the passed url has a valid host of proxer.
     */
    public boolean hasProxerHost(@Nonnull HttpUrl url) {
        return url.host().equals(webBase.host()) || url.host().equals(cdnBase.host()) ||
                proxerHostPattern.matcher(url.host()).matches();
    }
}
