package me.proxer.library.api.info;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.info.Relation;
import me.proxer.library.enums.*;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees.
 */
public class RelationsEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("relations.json")));

        final List<Relation> result = api.info()
                .relations("1")
                .build()
                .execute();

        assertThat(result).isEqualTo(buildTestRelations());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("relations.json")));

        api.info().relations("115")
                .includeHentai(true)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/info/relations?id=115&isH=true");
    }

    private List<Relation> buildTestRelations() {
        return Arrays.asList(
                new Relation("18598", "Sword Art Online dj - Asuna Kouryakubon",
                        EnumSet.of(Genre.ADULT), EnumSet.of(FskConstraint.FSK_18, FskConstraint.SEX), "N/A",
                        Medium.DOUJIN, 1, MediaState.FINISHED, 14, 3, 122,
                        Category.MANGA, License.UNKNOWN, EnumSet.of(MediaLanguage.ENGLISH), null, null),
                new Relation("4167", "Sword Art Online", EnumSet.of(Genre.ADVENTURE, Genre.ACTION,
                        Genre.COMEDY, Genre.DRAMA, Genre.FANTASY, Genre.ROMANCE, Genre.SCI_FI),
                        EnumSet.of(FskConstraint.FSK_12, FskConstraint.BAD_LANGUAGE, FskConstraint.VIOLENCE),
                        "Kazuto Kirigaya testet als einer der ersten einen neuen Hightech-Helm, welcher "
                                + "die Psyche des Nutzers komplett in die Welt des MMORPGs „Sword Art Online“ "
                                + "transferiert. Als Tester der Beta-Version besitzt er bereits einiges an Erfahrung "
                                + "und kämpfte sich mehr als erfolgreich als „Kirito“ durch die ersten Ebenen der "
                                + "Fantasy-Welt. Doch schon kurz nach der Eröffnung SAOs merken die Spieler, dass "
                                + "etwas nicht stimmt: Im Menü gibt es keinen Logout-Button. Hinter dem Grund der "
                                + "allgemeinen aufkommenden Panik scheint der Administrator des Spiels zu stecken "
                                + "und die einzige Möglichkeit wieder in die reale Welt zurückzukehren besteht darin, "
                                + "SAO erfolgreich abzuschließen. Doch das ist leichter gesagt als getan, denn der Tod "
                                + "in der Fantasy-Welt bedeutet auch den richtigen Tod in der richtigen Welt.",
                        Medium.ANIMESERIES, 25, MediaState.FINISHED, 158782, 17935,
                        5968, Category.ANIME, License.LICENSED, EnumSet.of(MediaLanguage.ENGLISH_SUB,
                        MediaLanguage.ENGLISH_DUB, MediaLanguage.GERMAN_DUB), 2012, Season.SUMMER)
        );
    }
}
