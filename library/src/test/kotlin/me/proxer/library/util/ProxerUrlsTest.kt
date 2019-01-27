package me.proxer.library.util

import me.proxer.library.enums.AnimeLanguage
import me.proxer.library.enums.Device
import me.proxer.library.enums.Language
import okhttp3.HttpUrl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class ProxerUrlsTest {

    @Test
    fun testWebBase() {
        assertThat(ProxerUrls.webBase.toString()).isEqualTo("https://proxer.me/")
    }

    @Test
    fun testApiBase() {
        assertThat(ProxerUrls.apiBase.toString()).isEqualTo("https://proxer.me/api/v1/")
    }

    @Test
    fun testCdnBase() {
        assertThat(ProxerUrls.cdnBase.toString()).isEqualTo("https://cdn.proxer.me/")
    }

    @Test
    fun testNewsImage() {
        assertThat(ProxerUrls.newsImage("1", "2").toString())
            .isEqualTo("https://cdn.proxer.me/news/tmp/1_2.png")
    }

    @Test
    fun testUserImage() {
        assertThat(ProxerUrls.userImage("1").toString())
            .isEqualTo("https://cdn.proxer.me/avatar/1")
    }

    @Test
    fun testEntryImage() {
        assertThat(ProxerUrls.entryImage("1").toString())
            .isEqualTo("https://cdn.proxer.me/cover/tmp/1.jpg")
    }

    @Test
    fun testProxyImage() {
        assertThat(ProxerUrls.proxyImage(HttpUrl.get("https://example.com/image.png")).toString())
            .isEqualTo("https://prxr.me/img/?url=https%3A%2F%2Fexample.com%2Fimage.png")
    }

    @Test
    fun testProxyImageString() {
        assertThat(ProxerUrls.proxyImage("https://example.com/image.png").toString())
            .isEqualTo("https://prxr.me/img/?url=https%3A%2F%2Fexample.com%2Fimage.png")
    }

    @Test
    fun testTranslatorGroupImage() {
        assertThat(ProxerUrls.translatorGroupImage("1").toString())
            .isEqualTo("https://cdn.proxer.me/translatorgroups/1.jpg")
    }

    @Test
    fun testIndustryImage() {
        assertThat(ProxerUrls.industryImage("1").toString())
            .isEqualTo("https://cdn.proxer.me/industry/1.jpg")
    }

    @Test
    fun testHosterImage() {
        assertThat(ProxerUrls.hosterImage("play.png").toString())
            .isEqualTo("https://proxer.me/images/hoster/play.png")
    }

    @Test
    fun testMangaPageImage() {
        assertThat(ProxerUrls.mangaPageImage("1", "2", "3", "SAO").toString())
            .isEqualTo("https://manga1.proxer.me/f/2/3/SAO")
    }

    @Test
    fun testDonateWeb() {
        assertThat(ProxerUrls.donateWeb().toString())
            .isEqualTo("https://proxer.me/donate?device=default")
    }

    @Test
    fun testDonateWebWithDevice() {
        assertThat(ProxerUrls.donateWeb(Device.DEFAULT).toString())
            .isEqualTo("https://proxer.me/donate?device=default")
    }

    @Test
    fun testWikiWeb() {
        assertThat(ProxerUrls.wikiWeb("test").toString())
            .isEqualTo("https://proxer.me/wiki/test?device=default")
    }

    @Test
    fun testUserWeb() {
        assertThat(ProxerUrls.userWeb("1").toString())
            .isEqualTo("https://proxer.me/user/1?device=default")
    }

    @Test
    fun testUserWebWithDevice() {
        assertThat(ProxerUrls.userWeb("2", Device.LEGACY_DESKTOP).toString())
            .isEqualTo("https://proxer.me/user/2?device=desktop")
    }

    @Test
    fun testForumWeb() {
        assertThat(ProxerUrls.forumWeb("1", "2").toString())
            .isEqualTo("https://proxer.me/forum/1/2?device=default")
    }

    @Test
    fun testForumWebWithDevice() {
        assertThat(ProxerUrls.forumWeb("1", "2", Device.MOBILE).toString())
            .isEqualTo("https://proxer.me/forum/1/2?device=mobile")
    }

    @Test
    fun testNewsWeb() {
        assertThat(ProxerUrls.newsWeb("4", "5").toString())
            .isEqualTo("https://proxer.me/forum/4/5?device=default")
    }

    @Test
    fun testNewsWebWithDevice() {
        assertThat(ProxerUrls.newsWeb("4", "5", Device.UNSPECIFIED).toString())
            .isEqualTo("https://proxer.me/forum/4/5?device=")
    }

    @Test
    fun testInfoWeb() {
        assertThat(ProxerUrls.infoWeb("332").toString())
            .isEqualTo("https://proxer.me/info/332?device=default")
    }

    @Test
    fun testInfoWebWithDevice() {
        assertThat(ProxerUrls.infoWeb("12", Device.MOBILE).toString())
            .isEqualTo("https://proxer.me/info/12?device=mobile")
    }

    @Test
    fun testIndustryWeb() {
        assertThat(ProxerUrls.industryWeb("453").toString())
            .isEqualTo("https://proxer.me/industry/453?device=default")
    }

    @Test
    fun testTranslatorGroupWeb() {
        assertThat(ProxerUrls.translatorGroupWeb("123").toString())
            .isEqualTo("https://proxer.me/translatorgroups/123?device=default")
    }

    @Test
    fun testAnimeWeb() {
        assertThat(ProxerUrls.animeWeb("1", 2, AnimeLanguage.OTHER).toString())
            .isEqualTo("https://proxer.me/watch/1/2/misc?device=default")
    }

    @Test
    fun testAnimeWebWithDevice() {
        assertThat(ProxerUrls.animeWeb("1", 2, AnimeLanguage.GERMAN_DUB, Device.LEGACY_HTML).toString())
            .isEqualTo("https://proxer.me/watch/1/2/gerdub?device=html")
    }

    @Test
    fun testMangaWeb() {
        assertThat(ProxerUrls.mangaWeb("19", 8, Language.ENGLISH).toString())
            .isEqualTo("https://proxer.me/chapter/19/8/en?device=default")
    }

    @Test
    fun testMangaWebWithDevice() {
        assertThat(ProxerUrls.mangaWeb("3", 4, Language.GERMAN, Device.LEGACY_DESKTOP).toString())
            .isEqualTo("https://proxer.me/chapter/3/4/de?device=desktop")
    }

    @Test
    fun testRegisterWeb() {
        assertThat(ProxerUrls.registerWeb().toString()).isEqualTo("https://proxer.me/register?device=default")
    }

    @Test
    fun testCaptchaWeb() {
        assertThat(ProxerUrls.captchaWeb().toString()).isEqualTo("https://proxer.me/misc/captcha?device=default")
    }

    @Test
    fun testCaptchaWebWithDevice() {
        assertThat(ProxerUrls.captchaWeb(Device.MOBILE).toString())
            .isEqualTo("https://proxer.me/misc/captcha?device=mobile")
    }

    @Test
    fun testHasProxerHost() {
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.get("https://proxer.me/test"))).isTrue()
    }

    @Test
    fun testHasProxerHostCdn() {
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.get("https://cdn.proxer.me/test"))).isTrue()
    }

    @Test
    fun testHasProxerHostProxy() {
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.get("https://prxr.me/test"))).isFalse()
    }

    @Test
    fun testHasProxerHostManga() {
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.get("https://manga1.proxer.me/f/test"))).isTrue()
    }

    @Test
    fun testHasProxerHostStream() {
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.get("https://stream.proxer.me/files/embed-abc.html"))).isTrue()
    }

    @Test
    fun testHasProxerHostFileStream() {
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.get("https://s39-ps.proxer.me/files/test.mp4"))).isTrue()
    }

    @Test
    fun testHasAlternativeProxerHostFileStream() {
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.get("https://s3.stream.proxer.me/files/test.mp4"))).isTrue()
    }

    @Test
    fun testHasProxerHostFalse() {
        assertThat(ProxerUrls.hasProxerHost(HttpUrl.get("https://example.me/test"))).isFalse()
    }
}
