package me.proxer.library.util;

import com.pushtorefresh.private_constructor_checker.PrivateConstructorChecker;
import me.proxer.library.enums.AnimeLanguage;
import me.proxer.library.enums.Device;
import me.proxer.library.enums.Language;
import okhttp3.HttpUrl;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class ProxerUrlsTest {

    @Test
    public void testWebBase() {
        assertThat(ProxerUrls.webBase().toString()).isEqualTo("https://proxer.me/");
    }

    @Test
    public void testApiBase() {
        assertThat(ProxerUrls.apiBase().toString()).isEqualTo("https://proxer.me/api/v1/");
    }

    @Test
    public void testCdnBase() {
        assertThat(ProxerUrls.cdnBase().toString()).isEqualTo("https://cdn.proxer.me/");
    }

    @Test
    public void testNewsImage() {
        assertThat(ProxerUrls.newsImage("1", "2").toString())
                .isEqualTo("https://cdn.proxer.me/news/tmp/1_2.png");
    }

    @Test
    public void testUserImage() {
        assertThat(ProxerUrls.userImage("1").toString())
                .isEqualTo("https://cdn.proxer.me/avatar/1");
    }

    @Test
    public void testEntryImage() {
        assertThat(ProxerUrls.entryImage("1").toString())
                .isEqualTo("https://cdn.proxer.me/cover/tmp/1.jpg");
    }

    @Test
    public void testProxyImage() {
        assertThat(ProxerUrls.proxyImage(HttpUrl.get("https://example.com/image.png")).toString())
                .isEqualTo("https://prxr.me/img/?url=https%3A%2F%2Fexample.com%2Fimage.png");
    }

    @Test
    public void testProxyImageString() {
        assertThat(ProxerUrls.proxyImage("https://example.com/image.png").toString())
                .isEqualTo("https://prxr.me/img/?url=https%3A%2F%2Fexample.com%2Fimage.png");
    }

    @Test
    public void testTranslatorGroupImage() {
        assertThat(ProxerUrls.translatorGroupImage("1").toString())
                .isEqualTo("https://cdn.proxer.me/translatorgroups/1.jpg");
    }

    @Test
    public void testIndustryImage() {
        assertThat(ProxerUrls.industryImage("1").toString())
                .isEqualTo("https://cdn.proxer.me/industry/1.jpg");
    }

    @Test
    public void testHosterImage() {
        assertThat(ProxerUrls.hosterImage("play.png").toString())
                .isEqualTo("https://proxer.me/images/hoster/play.png");
    }

    @Test
    public void testMangaPageImage() {
        assertThat(ProxerUrls.mangaPageImage("1", "2", "3", "SAO").toString())
                .isEqualTo("https://manga1.proxer.me/f/2/3/SAO");
    }

    @Test
    public void testDonateWeb() {
        assertThat(ProxerUrls.donateWeb().toString())
                .isEqualTo("https://proxer.me/donate?device=default");
    }

    @Test
    public void testDonateWebWithDevice() {
        assertThat(ProxerUrls.donateWeb(Device.DEFAULT).toString())
                .isEqualTo("https://proxer.me/donate?device=default");
    }

    @Test
    public void testWikiWeb() {
        assertThat(ProxerUrls.wikiWeb("test").toString())
                .isEqualTo("https://proxer.me/wiki/test?device=default");
    }

    @Test
    public void testUserWeb() {
        assertThat(ProxerUrls.userWeb("1").toString())
                .isEqualTo("https://proxer.me/user/1?device=default");
    }

    @Test
    public void testUserWebWithDevice() {
        assertThat(ProxerUrls.userWeb("2", Device.LEGACY_DESKTOP).toString())
                .isEqualTo("https://proxer.me/user/2?device=desktop");
    }

    @Test
    public void testForumWeb() {
        assertThat(ProxerUrls.forumWeb("1", "2").toString())
                .isEqualTo("https://proxer.me/forum/1/2?device=default");
    }

    @Test
    public void testForumWebWithDevice() {
        assertThat(ProxerUrls.forumWeb("1", "2", Device.MOBILE).toString())
                .isEqualTo("https://proxer.me/forum/1/2?device=mobile");
    }

    @Test
    public void testNewsWeb() {
        assertThat(ProxerUrls.newsWeb("4", "5").toString())
                .isEqualTo("https://proxer.me/forum/4/5?device=default");
    }

    @Test
    public void testNewsWebWithDevice() {
        assertThat(ProxerUrls.newsWeb("4", "5", Device.UNSPECIFIED).toString())
                .isEqualTo("https://proxer.me/forum/4/5?device=");
    }

    @Test
    public void testInfoWeb() {
        assertThat(ProxerUrls.infoWeb("332").toString())
                .isEqualTo("https://proxer.me/info/332?device=default");
    }

    @Test
    public void testInfoWebWithDevice() {
        assertThat(ProxerUrls.infoWeb("12", Device.MOBILE).toString())
                .isEqualTo("https://proxer.me/info/12?device=mobile");
    }

    @Test
    public void testIndustryWeb() {
        assertThat(ProxerUrls.industryWeb("453").toString())
                .isEqualTo("https://proxer.me/industry/453?device=default");
    }

    @Test
    public void testTranslatorGroupWeb() {
        assertThat(ProxerUrls.translatorGroupWeb("123").toString())
                .isEqualTo("https://proxer.me/translatorgroups/123?device=default");
    }

    @Test
    public void testAnimeWeb() {
        assertThat(ProxerUrls.animeWeb("1", 2, AnimeLanguage.OTHER).toString())
                .isEqualTo("https://proxer.me/watch/1/2/misc?device=default");
    }

    @Test
    public void testAnimeWebWithDevice() {
        assertThat(ProxerUrls.animeWeb("1", 2, AnimeLanguage.GERMAN_DUB, Device.LEGACY_HTML).toString())
                .isEqualTo("https://proxer.me/watch/1/2/gerdub?device=html");
    }

    @Test
    public void testMangaWeb() {
        assertThat(ProxerUrls.mangaWeb("19", 8, Language.ENGLISH).toString())
                .isEqualTo("https://proxer.me/chapter/19/8/en?device=default");
    }

    @Test
    public void testMangaWebWithDevice() {
        assertThat(ProxerUrls.mangaWeb("3", 4, Language.GERMAN, Device.LEGACY_DESKTOP).toString())
                .isEqualTo("https://proxer.me/chapter/3/4/de?device=desktop");
    }

    @Test
    public void testCaptchaWeb() {
        assertThat(ProxerUrls.captchaWeb().toString()).isEqualTo("https://proxer.me/misc/captcha?device=default");
    }

    @Test
    public void testCaptchaWebWithDevice() {
        assertThat(ProxerUrls.captchaWeb(Device.MOBILE).toString())
                .isEqualTo("https://proxer.me/misc/captcha?device=mobile");
    }

    @Test
    public void testHasProxerHost() {
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.get("https://proxer.me/test"))).isTrue();
    }

    @Test
    public void testHasProxerHostCdn() {
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.get("https://cdn.proxer.me/test"))).isTrue();
    }

    @Test
    public void testHasProxerHostProxy() {
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.get("https://prxr.me/test"))).isTrue();
    }

    @Test
    public void testHasProxerHostManga() {
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.get("https://manga1.proxer.me/f/test"))).isTrue();
    }

    @Test
    public void testHasProxerHostStream() {
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.get("https://stream.proxer.me/files/embed-abc.html"))).isTrue();
    }

    @Test
    public void testHasProxerHostFileStream() {
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.get("https://s39.stream.proxer.me/files/test.mp4"))).isTrue();
    }

    @Test
    public void testHasProxerHostFalse() {
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.get("https://example.me/test"))).isFalse();
    }

    @Test
    public void testIsUtilityClass() {
        PrivateConstructorChecker
                .forClass(ProxerUrls.class)
                .expectedTypeOfException(UnsupportedOperationException.class)
                .check();
    }
}
