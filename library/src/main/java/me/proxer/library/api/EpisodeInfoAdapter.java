package me.proxer.library.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;
import com.squareup.moshi.ToJson;
import me.proxer.library.api.EpisodeInfoAdapter.IntermediateEpisodeInfo.IntermediateEpisode;
import me.proxer.library.entity.info.AnimeEpisode;
import me.proxer.library.entity.info.Episode;
import me.proxer.library.entity.info.EpisodeInfo;
import me.proxer.library.entity.info.MangaEpisode;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;

import javax.annotation.Nullable;
import java.util.*;

/**
 * @author Ruben Gees
 */
class EpisodeInfoAdapter {

    private static final String DELIMITER = ",";

    @FromJson
    EpisodeInfo fromJson(final IntermediateEpisodeInfo json) {
        final List<Episode> episodes = new ArrayList<>(json.episodes.size());

        for (IntermediateEpisode episode : json.episodes) {
            if (json.category == Category.ANIME) {
                episodes.add(new AnimeEpisode(episode.number, episode.language,
                        new HashSet<>(Arrays.asList(episode.hosters.split(DELIMITER))),
                        Arrays.asList(episode.hosterImages.split(DELIMITER))));
            } else {
                episodes.add(new MangaEpisode(episode.number, episode.language, episode.title));
            }
        }

        return new EpisodeInfo(json.firstEpisode, json.lastEpisode, json.category, json.availableLanguages,
                json.userState, episodes);
    }

    @SuppressWarnings("unused")
    @ToJson
    String toJson(@SuppressWarnings("unused") @Nullable final EpisodeInfo episodeInfo) {
        // Moshi somehow needs this method for this case, we don't use it though.
        throw new UnsupportedOperationException();
    }

    static class IntermediateEpisodeInfo {

        @Json(name = "start")
        int firstEpisode;

        @Json(name = "end")
        int lastEpisode;

        @Json(name = "kat")
        Category category;

        @Json(name = "lang")
        Set<MediaLanguage> availableLanguages;

        @Json(name = "state")
        int userState;

        @Json(name = "episodes")
        List<IntermediateEpisode> episodes;

        static class IntermediateEpisode {

            @Json(name = "no")
            int number;

            @Json(name = "typ")
            MediaLanguage language;

            @Json(name = "title")
            String title;

            @Json(name = "types")
            String hosters;

            @Json(name = "typeimg")
            String hosterImages;
        }
    }
}
