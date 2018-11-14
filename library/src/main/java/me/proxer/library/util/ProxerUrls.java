package me.proxer.library.util;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.UtilityClass;
import me.proxer.library.entity.notifications.NewsArticle;
import me.proxer.library.enums.AnimeLanguage;
import me.proxer.library.enums.Device;
import me.proxer.library.enums.Language;
import okhttp3.HttpUrl;

import java.util.regex.Pattern;

/**
 * Utility class with various static methods for retrieving web and image links.
 * Moreover it holds the base urls of web, api and image cdn.
 *
 * @author Ruben Gees
 */
@UtilityClass
@Accessors(fluent = true)
public final class ProxerUrls {

    private static final Pattern PROXER_STREAM_FILE_HOST_PATTERN = Pattern
            .compile("s[0-9]+\\.(ps|stream)\\.proxer\\.me");

    private static final Pattern PROXER_MANGA_HOST_PATTERN = Pattern
            .compile("manga[0-9]+\\.proxer\\.me");

    /**
     * Returns the base url for all web pages.
     */
    @Getter
    private final HttpUrl webBase = new HttpUrl.Builder()
            .scheme("https")
            .host("proxer.me")
            .addPathSegment("")
            .build();

    /**
     * Returns the base url for all api calls.
     */
    @Getter
    private final HttpUrl apiBase = webBase.newBuilder()
            .addPathSegment("api")
            .addPathSegment("v1")
            .addPathSegment("")
            .build();

    /**
     * Returns the base url for all image links.
     */
    @Getter
    private final HttpUrl cdnBase = new HttpUrl.Builder()
            .scheme("https")
            .host("cdn.proxer.me")
            .addPathSegment("")
            .build();

    /**
     * Returns the base url for all image links.
     */
    @Getter
    private final HttpUrl streamBase = new HttpUrl.Builder()
            .scheme("https")
            .host("stream.proxer.me")
            .addPathSegment("")
            .build();

    /**
     * Returns the base url for all proxy links.
     */
    private final HttpUrl proxyBase = new HttpUrl.Builder()
            .scheme("https")
            .host("prxr.me")
            .addPathSegment("img")
            .addPathSegment("")
            .build();

    /**
     * Returns the image link of a {@link NewsArticle}, based on its
     * {@code id} and {@code image}.
     */
    public HttpUrl newsImage(final String id, final String image) {
        return cdnBase.newBuilder()
                .addPathSegment("news")
                .addPathSegment("tmp")
                .addPathSegment(String.format("%s_%s.png", id, image))
                .build();
    }

    /**
     * Returns the image link of the user.
     */
    public HttpUrl userImage(final String image) {
        return cdnBase.newBuilder()
                .addPathSegment("avatar")
                .addPathSegment(image)
                .build();
    }

    /**
     * Returns the cover image link of the entry.
     */
    public HttpUrl entryImage(final String id) {
        return cdnBase.newBuilder()
                .addPathSegment("cover")
                .addPathSegment("tmp")
                .addPathSegment(String.format("%s.jpg", id))
                .build();
    }

    /**
     * Returns the link to the image behind the proxy.
     */
    public HttpUrl proxyImage(final HttpUrl url) {
        return proxyBase.newBuilder()
                .addQueryParameter("url", url.toString())
                .build();
    }

    /**
     * Returns the link to the image behind the proxy.
     */
    public HttpUrl proxyImage(final String url) {
        return proxyBase.newBuilder()
                .addQueryParameter("url", url)
                .build();
    }

    /**
     * Returns the image link of the translator group.
     */
    public HttpUrl translatorGroupImage(final String id) {
        return cdnBase.newBuilder()
                .addPathSegment("translatorgroups")
                .addPathSegment(String.format("%s.jpg", id))
                .build();
    }

    /**
     * Returns the image link of the industry.
     */
    public HttpUrl industryImage(final String id) {
        return cdnBase.newBuilder()
                .addPathSegment("industry")
                .addPathSegment(String.format("%s.jpg", id))
                .build();
    }

    /**
     * Returns the image link of the hoster.
     */
    public HttpUrl hosterImage(final String id) {
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
    public HttpUrl mangaPageImage(final String server, final String entryId, final String id, final String name) {
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
    public HttpUrl donateWeb(final Device device) {
        return webBase.newBuilder()
                .addPathSegment("donate")
                .addQueryParameter("device", ProxerUtils.getSafeApiEnumName(device))
                .build();
    }

    /**
     * Returns the link for the donation web page.
     */
    public HttpUrl donateWeb() {
        return donateWeb(Device.DEFAULT);
    }

    /**
     * Returns the link for the wiki web page, based on the {@code topic}.
     */
    public HttpUrl wikiWeb(final String topic) {
        return webBase.newBuilder()
                .addPathSegment("wiki")
                .addPathSegment(topic)
                .addQueryParameter("device", ProxerUtils.getSafeApiEnumName(Device.DEFAULT))
                .build();
    }

    /**
     * Returns the link for the user's web page.
     */
    public HttpUrl userWeb(final String id, final Device device) {
        return webBase.newBuilder()
                .addPathSegment("user")
                .addPathSegment(id)
                .addQueryParameter("device", ProxerUtils.getSafeApiEnumName(device))
                .build();
    }

    /**
     * Returns the link for the user's web page.
     */
    public HttpUrl userWeb(final String id) {
        return userWeb(id, Device.DEFAULT);
    }

    /**
     * Returns the link for the web page of the requested forum thread, residing in the category, specified by the
     * {@code categoryId}.
     */
    public HttpUrl forumWeb(final String categoryId, final String threadId, final Device device) {
        return webBase.newBuilder()
                .addPathSegment("forum")
                .addPathSegment(categoryId)
                .addPathSegment(threadId)
                .addQueryParameter("device", ProxerUtils.getSafeApiEnumName(device))
                .build();
    }

    /**
     * Returns the link for the web page of the requested forum thread, residing in the category, specified by the
     * {@code categoryId}.
     */
    public HttpUrl forumWeb(final String categoryId, final String threadId) {
        return forumWeb(categoryId, threadId, Device.DEFAULT);
    }

    /**
     * Returns the link for the web page of the requested news article, residing in the category, specified by the
     * {@code categoryId}.
     */
    public HttpUrl newsWeb(final String categoryId, final String threadId, final Device device) {
        return forumWeb(categoryId, threadId, device);
    }

    /**
     * Returns the link for the web page of the requested news article, residing in the category, specified by the
     * {@code categoryId}.
     */
    public HttpUrl newsWeb(final String categoryId, final String threadId) {
        return forumWeb(categoryId, threadId);
    }

    /**
     * Returns the link for the web page of the requested info page.
     */
    public HttpUrl infoWeb(final String id, final Device device) {
        return webBase.newBuilder()
                .addPathSegment("info")
                .addPathSegment(id)
                .addQueryParameter("device", ProxerUtils.getSafeApiEnumName(device))
                .build();
    }

    /**
     * Returns the link for the web page of the requested industry.
     */
    public HttpUrl infoWeb(final String id) {
        return infoWeb(id, Device.DEFAULT);
    }

    /**
     * Returns the link for the web page of the requested industry.
     */
    public HttpUrl industryWeb(final String id) {
        return webBase.newBuilder()
                .addPathSegment("industry")
                .addPathSegment(id)
                .addQueryParameter("device", ProxerUtils.getSafeApiEnumName(Device.DEFAULT))
                .build();
    }

    /**
     * Returns the link for the web page of the requested translator group.
     */
    public HttpUrl translatorGroupWeb(final String id) {
        return webBase.newBuilder()
                .addPathSegment("translatorgroups")
                .addPathSegment(id)
                .addQueryParameter("device", ProxerUtils.getSafeApiEnumName(Device.DEFAULT))
                .build();
    }

    /**
     * Returns the link for the watch page of an anime.
     */
    public HttpUrl animeWeb(final String id, final int episode, final AnimeLanguage language, final Device device) {
        return webBase.newBuilder()
                .addPathSegment("watch")
                .addPathSegment(id)
                .addPathSegment(String.valueOf(episode))
                .addPathSegment(ProxerUtils.getSafeApiEnumName(language))
                .addQueryParameter("device", ProxerUtils.getSafeApiEnumName(device))
                .build();
    }

    /**
     * Returns the link for the watch page of an anime.
     */
    public HttpUrl animeWeb(final String id, final int episode, final AnimeLanguage language) {
        return animeWeb(id, episode, language, Device.DEFAULT);
    }

    /**
     * Returns the link for the read page of a manga.
     */
    public HttpUrl mangaWeb(final String id, final int episode, final Language language, final Device device) {
        return webBase.newBuilder()
                .addPathSegment("chapter")
                .addPathSegment(id)
                .addPathSegment(String.valueOf(episode))
                .addPathSegment(ProxerUtils.getSafeApiEnumName(language))
                .addQueryParameter("device", ProxerUtils.getSafeApiEnumName(device))
                .build();
    }

    /**
     * Returns the link for the read page of a manga.
     */
    public HttpUrl mangaWeb(final String id, final int episode, final Language language) {
        return mangaWeb(id, episode, language, Device.DEFAULT);
    }

    /**
     * Returns the link for the web page to solve the captcha.
     */
    public HttpUrl captchaWeb(final Device device) {
        return webBase.newBuilder()
                .addPathSegment("misc")
                .addPathSegment("captcha")
                .addQueryParameter("device", ProxerUtils.getSafeApiEnumName(device))
                .build();
    }

    /**
     * Returns the link for the web page to register.
     */
    public HttpUrl registerWeb() {
        return webBase.newBuilder()
                .addPathSegment("register")
                .addQueryParameter("device", ProxerUtils.getSafeApiEnumName(Device.DEFAULT))
                .build();
    }

    /**
     * Returns the link for the web page to solve the captcha.
     */
    public HttpUrl captchaWeb() {
        return captchaWeb(Device.DEFAULT);
    }

    /**
     * Returns if the passed url has a valid host of proxer.
     */
    public boolean hasProxerHost(final HttpUrl url) {
        return hasProxerWebOrCdnOrStreamHost(url) || hasProxerStreamFileHost(url) || hasProxerMangaHost(url);
    }

    /**
     * Returns if the passed url has a valid host of proxer or the proxer cdn.
     */
    public boolean hasProxerWebOrCdnOrStreamHost(final HttpUrl url) {
        return url.host().equals(webBase.host()) || url.host().equals(cdnBase.host())
                || url.host().equals(streamBase.host());
    }

    /**
     * Returns if the passed url has a valid proxer stream host.
     */
    public boolean hasProxerStreamFileHost(final HttpUrl url) {
        return PROXER_STREAM_FILE_HOST_PATTERN.matcher(url.host()).matches();
    }

    /**
     * Returns if the passed url has a valid proxer manga host.
     */
    public boolean hasProxerMangaHost(final HttpUrl url) {
        return PROXER_MANGA_HOST_PATTERN.matcher(url.host()).matches();
    }
}
