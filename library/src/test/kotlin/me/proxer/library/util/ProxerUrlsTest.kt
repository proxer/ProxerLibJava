package me.proxer.library.util

import me.proxer.library.enums.AnimeLanguage
import me.proxer.library.enums.Device
import me.proxer.library.enums.Language
import okhttp3.HttpUrl
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class ProxerUrlsTest {

    @Test
    fun testWebBase() {
        ProxerUrls.webBase.toString() shouldEqual "https://proxer.me/"
    }

    @Test
    fun testApiBase() {
        ProxerUrls.apiBase.toString() shouldEqual "https://proxer.me/api/v1/"
    }

    @Test
    fun testCdnBase() {
        ProxerUrls.cdnBase.toString() shouldEqual "https://cdn.proxer.me/"
    }

    @Test
    fun testNewsImage() {
        ProxerUrls.newsImage("1", "2").toString() shouldEqual "https://cdn.proxer.me/news/tmp/1_2.png"
    }

    @Test
    fun testUserImage() {
        ProxerUrls.userImage("1").toString() shouldEqual "https://cdn.proxer.me/avatar/1"
    }

    @Test
    fun testEntryImage() {
        ProxerUrls.entryImage("1").toString() shouldEqual "https://cdn.proxer.me/cover/tmp/1.jpg"
    }

    @Test
    fun testProxyImage() {
        ProxerUrls.proxyImage(HttpUrl.get("https://example.com/image.png")).toString() shouldEqual
            "https://proxy.proxer.me/index.php?url=https%3A%2F%2Fexample.com%2Fimage.png"
    }

    @Test
    fun testProxyImageString() {
        ProxerUrls.proxyImage("https://example.com/image.png").toString() shouldEqual
            "https://proxy.proxer.me/index.php?url=https%3A%2F%2Fexample.com%2Fimage.png"
    }

    @Test
    fun testTranslatorGroupImage() {
        ProxerUrls.translatorGroupImage("1").toString() shouldEqual "https://cdn.proxer.me/translatorgroups/1.jpg"
    }

    @Test
    fun testIndustryImage() {
        ProxerUrls.industryImage("1").toString() shouldEqual "https://cdn.proxer.me/industry/1.jpg"
    }

    @Test
    fun testHosterImage() {
        ProxerUrls.hosterImage("play.png").toString() shouldEqual "https://proxer.me/images/hoster/play.png"
    }

    @Test
    fun testMangaPageImage() {
        ProxerUrls.mangaPageImage("1", "2", "3", "SAO").toString() shouldEqual
            "https://manga1.proxer.me/f/2/3/SAO"
    }

    @Test
    fun testDonateWeb() {
        ProxerUrls.donateWeb().toString() shouldEqual "https://proxer.me/donate?device=default"
    }

    @Test
    fun testDonateWebWithDevice() {
        ProxerUrls.donateWeb(Device.DEFAULT).toString() shouldEqual "https://proxer.me/donate?device=default"
    }

    @Test
    fun testWikiWeb() {
        ProxerUrls.wikiWeb("test").toString() shouldEqual "https://proxer.me/wiki/test?device=default"
    }

    @Test
    fun testUserWeb() {
        ProxerUrls.userWeb("1").toString() shouldEqual "https://proxer.me/user/1?device=default"
    }

    @Test
    fun testUserWebWithDevice() {
        ProxerUrls.userWeb("2", Device.LEGACY_DESKTOP).toString() shouldEqual
            "https://proxer.me/user/2?device=desktop"
    }

    @Test
    fun testForumWeb() {
        ProxerUrls.forumWeb("1", "2").toString() shouldEqual
            "https://proxer.me/forum/1/2?device=default"
    }

    @Test
    fun testForumWebWithDevice() {
        ProxerUrls.forumWeb("1", "2", Device.MOBILE).toString() shouldEqual
            "https://proxer.me/forum/1/2?device=mobile"
    }

    @Test
    fun testNewsWeb() {
        ProxerUrls.newsWeb("4", "5").toString() shouldEqual
            "https://proxer.me/forum/4/5?device=default"
    }

    @Test
    fun testNewsWebWithDevice() {
        ProxerUrls.newsWeb("4", "5", Device.UNSPECIFIED).toString() shouldEqual
            "https://proxer.me/forum/4/5?device="
    }

    @Test
    fun testInfoWeb() {
        ProxerUrls.infoWeb("332").toString() shouldEqual "https://proxer.me/info/332?device=default"
    }

    @Test
    fun testInfoWebWithDevice() {
        ProxerUrls.infoWeb("12", Device.MOBILE).toString() shouldEqual
            "https://proxer.me/info/12?device=mobile"
    }

    @Test
    fun testIndustryWeb() {
        ProxerUrls.industryWeb("453").toString() shouldEqual "https://proxer.me/industry/453?device=default"
    }

    @Test
    fun testTranslatorGroupWeb() {
        ProxerUrls.translatorGroupWeb("123").toString() shouldEqual
            "https://proxer.me/translatorgroups/123?device=default"
    }

    @Test
    fun testAnimeWeb() {
        ProxerUrls.animeWeb("1", 2, AnimeLanguage.OTHER).toString() shouldEqual
            "https://proxer.me/watch/1/2/misc?device=default"
    }

    @Test
    fun testAnimeWebWithDevice() {
        ProxerUrls.animeWeb("1", 2, AnimeLanguage.GERMAN_DUB, Device.LEGACY_HTML).toString() shouldEqual
            "https://proxer.me/watch/1/2/gerdub?device=html"
    }

    @Test
    fun testMangaWeb() {
        ProxerUrls.mangaWeb("19", 8, Language.ENGLISH).toString() shouldEqual
            "https://proxer.me/chapter/19/8/en?device=default"
    }

    @Test
    fun testMangaWebWithDevice() {
        ProxerUrls.mangaWeb("3", 4, Language.GERMAN, Device.LEGACY_DESKTOP).toString() shouldEqual
            "https://proxer.me/chapter/3/4/de?device=desktop"
    }

    @Test
    fun testRegisterWeb() {
        ProxerUrls.registerWeb().toString() shouldEqual "https://proxer.me/register?device=default"
    }

    @Test
    fun testCaptchaWeb() {
        ProxerUrls.captchaWeb().toString() shouldEqual "https://proxer.me/misc/captcha?device=default"
    }

    @Test
    fun testCaptchaWebWithDevice() {
        ProxerUrls.captchaWeb(Device.MOBILE).toString() shouldEqual "https://proxer.me/misc/captcha?device=mobile"
    }

    @Test
    fun testHasProxerHost() {
        ProxerUrls.hasProxerHost(HttpUrl.get("https://proxer.me/test")) shouldBe true
    }

    @Test
    fun testHasProxerHostCdn() {
        ProxerUrls.hasProxerHost(HttpUrl.get("https://cdn.proxer.me/test")) shouldBe true
    }

    @Test
    fun testHasProxerHostProxy() {
        ProxerUrls.hasProxerHost(HttpUrl.get("https://proxy.proxer.me/index.php?url=test")) shouldBe true
    }

    @Test
    fun testHasProxerHostManga() {
        ProxerUrls.hasProxerHost(HttpUrl.get("https://manga1.proxer.me/f/test")) shouldBe true
    }

    @Test
    fun testHasProxerHostStream() {
        ProxerUrls.hasProxerHost(HttpUrl.get("https://stream.proxer.me/files/embed-abc.html")) shouldBe true
    }

    @Test
    fun testHasProxerHostFileStream() {
        ProxerUrls.hasProxerHost(HttpUrl.get("https://s39-ps.proxer.me/files/test.mp4")) shouldBe true
    }

    @Test
    fun testHasDotSeparatedProxerHostFileStream() {
        ProxerUrls.hasProxerHost(HttpUrl.get("https://s39.ps.proxer.me/files/test.mp4")) shouldBe true
    }

    @Test
    fun testHasNewProxerHostFileStream() {
        ProxerUrls.hasProxerHost(HttpUrl.get("https://s39-psc.proxer.me/files/test.mp4")) shouldBe true
    }

    @Test
    fun testHasAlternativeProxerHostFileStream() {
        ProxerUrls.hasProxerHost(HttpUrl.get("https://s3.stream.proxer.me/files/test.mp4")) shouldBe true
    }

    @Test
    fun testHasProxerHostFileStreamFalse() {
        ProxerUrls.hasProxerHost(HttpUrl.get("https://s39-psfalse.proxer.me/files/test.mp4")) shouldBe false
    }

    @Test
    fun testHasProxerHostFalse() {
        ProxerUrls.hasProxerHost(HttpUrl.get("https://example.me/test")) shouldBe false
    }
}
