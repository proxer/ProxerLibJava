package com.proxerme.library.util;

import com.proxerme.library.enums.Device;
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
                .isEqualTo("https://cdn.proxer.me/news/tmp/1_2.png/");
    }

    @Test
    public void userImage() {
        assertThat(ProxerUrls.userImage("1").toString())
                .isEqualTo("https://cdn.proxer.me/avatar/1/");
    }

    @Test
    public void entryImage() {
        assertThat(ProxerUrls.entryImage("1").toString())
                .isEqualTo("https://cdn.proxer.me/cover/tmp/1.jpg/");
    }

    @Test
    public void translatorGroupImage() {
        assertThat(ProxerUrls.translatorGroupImage("1").toString())
                .isEqualTo("https://cdn.proxer.me/translatorgroups/1.jpg/");
    }

    @Test
    public void industryImage() {
        assertThat(ProxerUrls.industryImage("1").toString())
                .isEqualTo("https://cdn.proxer.me/industry/1.jpg/");
    }

    @Test
    public void hosterImage() {
        assertThat(ProxerUrls.hosterImage("1").toString())
                .isEqualTo("https://cdn.proxer.me/images/hoster/1/");
    }

    @Test
    public void mangaPageImage() {
        assertThat(ProxerUrls.mangaPageImage("1", "2", "3", "SAO").toString())
                .isEqualTo("https://manga1.proxer.me/f/2/3/SAO/");
    }

    @Test
    public void donateWeb() {
        assertThat(ProxerUrls.donateWeb().toString())
                .isEqualTo("https://proxer.me/donate/");
    }

    @Test
    public void donateWebWithDevice() {
        assertThat(ProxerUrls.donateWeb(Device.DEFAULT).toString())
                .isEqualTo("https://proxer.me/donate/?device=default");
    }

    @Test
    public void wikiWeb() {
        assertThat(ProxerUrls.wikiWeb("test").toString())
                .isEqualTo("https://proxer.me/wiki/test/");
    }

    @Test
    public void userWeb() {
        assertThat(ProxerUrls.userWeb("1").toString())
                .isEqualTo("https://proxer.me/user/1/");
    }

    @Test
    public void userWebWithDevice() {
        assertThat(ProxerUrls.userWeb("2", Device.DEFAULT).toString())
                .isEqualTo("https://proxer.me/user/2/?device=default");
    }

    @Test
    public void forumWeb() {
        assertThat(ProxerUrls.forumWeb("1", "2").toString())
                .isEqualTo("https://proxer.me/forum/1/2/");
    }

    @Test
    public void forumWebWithDevice() {
        assertThat(ProxerUrls.forumWeb("1", "2", Device.MOBILE).toString())
                .isEqualTo("https://proxer.me/forum/1/2/?device=mobile");
    }

    @Test
    public void newsWeb() {
        assertThat(ProxerUrls.newsWeb("4", "5").toString())
                .isEqualTo("https://proxer.me/forum/4/5/");
    }

    @Test
    public void newsWebWithDevice() {
        assertThat(ProxerUrls.newsWeb("4", "5", Device.MOBILE).toString())
                .isEqualTo("https://proxer.me/forum/4/5/?device=mobile");
    }
}
