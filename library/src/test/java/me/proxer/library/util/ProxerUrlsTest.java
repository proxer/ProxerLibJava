package me.proxer.library.util;

import com.pushtorefresh.private_constructor_checker.PrivateConstructorChecker;
import me.proxer.library.enums.Device;
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
                .isEqualTo("https://proxer.me/donate");
    }

    @Test
    public void testDonateWebWithDevice() {
        assertThat(ProxerUrls.donateWeb(Device.DEFAULT).toString())
                .isEqualTo("https://proxer.me/donate?device=default");
    }

    @Test
    public void testWikiWeb() {
        assertThat(ProxerUrls.wikiWeb("test").toString())
                .isEqualTo("https://proxer.me/wiki/test");
    }

    @Test
    public void testUserWeb() {
        assertThat(ProxerUrls.userWeb("1").toString())
                .isEqualTo("https://proxer.me/user/1");
    }

    @Test
    public void testUserWebWithDevice() {
        assertThat(ProxerUrls.userWeb("2", Device.DEFAULT).toString())
                .isEqualTo("https://proxer.me/user/2?device=default");
    }

    @Test
    public void testForumWeb() {
        assertThat(ProxerUrls.forumWeb("1", "2").toString())
                .isEqualTo("https://proxer.me/forum/1/2");
    }

    @Test
    public void testForumWebWithDevice() {
        assertThat(ProxerUrls.forumWeb("1", "2", Device.MOBILE).toString())
                .isEqualTo("https://proxer.me/forum/1/2?device=mobile");
    }

    @Test
    public void testNewsWeb() {
        assertThat(ProxerUrls.newsWeb("4", "5").toString())
                .isEqualTo("https://proxer.me/forum/4/5");
    }

    @Test
    public void testNewsWebWithDevice() {
        assertThat(ProxerUrls.newsWeb("4", "5", Device.UNSPECIFIED).toString())
                .isEqualTo("https://proxer.me/forum/4/5?device=");
    }

    @Test
    public void testCaptchaWeb() {
        assertThat(ProxerUrls.captchaWeb().toString()).isEqualTo("https://proxer.me/misc/captcha");
    }

    @Test
    public void testCaptchaWebWithDevice() {
        assertThat(ProxerUrls.captchaWeb(Device.MOBILE).toString())
                .isEqualTo("https://proxer.me/misc/captcha?device=mobile");
    }

    @Test
    public void testHasProxerHost() {
        //noinspection ConstantConditions
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.parse("https://proxer.me/test"))).isTrue();
    }

    @Test
    public void testHasProxerHostCdn() {
        //noinspection ConstantConditions
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.parse("https://cdn.proxer.me/test"))).isTrue();
    }

    @Test
    public void testHasProxerHostManga() {
        //noinspection ConstantConditions
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.parse("https://manga1.proxer.me/f/test"))).isTrue();
    }

    @Test
    public void testHasProxerHostFalse() {
        //noinspection ConstantConditions
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.parse("https://example.me/test"))).isFalse();
    }

    @Test
    public void testIsUtilityClass() {
        PrivateConstructorChecker
                .forClass(ProxerUrls.class)
                .expectedTypeOfException(UnsupportedOperationException.class)
                .check();
    }
}
