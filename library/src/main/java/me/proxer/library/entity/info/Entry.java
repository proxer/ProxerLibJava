package me.proxer.library.entity.info;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.entity.list.IndustryCore;
import me.proxer.library.enums.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

/**
 * Entity holding all info of an entry. This is everything what {@link EntryCore} contains and some
 * more which could also be retrieved by the respective standalone APIs.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class Entry implements ProxerIdItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the name.
     */
    @Json(name = "name")
    private String name;

    /**
     * Returns the genres.
     */
    @Json(name = "genre")
    private Set<Genre> genres;

    /**
     * Returns the fsk ratings.
     */
    @Json(name = "fsk")
    private Set<FskConstraint> fskConstraints;

    /**
     * Returns the description.
     */
    @Json(name = "description")
    private String description;

    /**
     * Returns the medium
     */
    @Json(name = "medium")
    private Medium medium;

    /**
     * Returns the amount of episodes, this entry has. They do not necessarily have to be uploaded.
     */
    @Json(name = "count")
    private int episodeAmount;

    /**
     * Returns the current state.
     */
    @Json(name = "state")
    private MediaState state;

    /**
     * Returns the sum of all ratings.
     */
    @Json(name = "rate_sum")
    private int ratingSum;

    /**
     * Returns the amount of ratings.
     */
    @Json(name = "rate_count")
    private int ratingAmount;

    /**
     * Returns the clicks on this entry, in this season.
     */
    @Json(name = "clicks")
    private int clicks;

    /**
     * Returns the category.
     */
    @Json(name = "kat")
    private Category category;

    /**
     * Returns the type of license.
     */
    @Json(name = "license")
    private License license;

    /**
     * Returns information regarding the adaption of this entry.
     */
    @Json(name = "adaption_data")
    private AdaptionInfo adaptionInfo;

    /**
     * Returns true, if this entry is age restricted.
     */
    @Json(name = "ageRestricted")
    private boolean ageRestricted;

    /**
     * Returns the synonyms in different languages.
     */
    @Json(name = "names")
    private List<Synonym> synonyms;

    /**
     * Returns the languages, this entry is available in.
     */
    @Json(name = "lang")
    private Set<MediaLanguage> languages;

    /**
     * Returns the seasons, this entry was aired. If present, the first in the list is the start and the second the end.
     */
    @Json(name = "seasons")
    private List<EntrySeasonInfo> seasons;

    /**
     * Returns the translator groups.
     */
    @Json(name = "groups")
    private List<EntryTranslatorGroup> translatorGroups;

    /**
     * Returns the industries involved in production and marketing.
     */
    @Json(name = "publisher")
    private List<IndustryCore> industries;

    /**
     * Returns the tags.
     */
    @Json(name = "tags")
    private List<InfoTag> tags;

    /**
     * Returns the average of all ratings.
     */
    public final float getRating() {
        if (ratingAmount <= 0) {
            return 0;
        } else {
            return ratingSum / (float) ratingAmount;
        }
    }
}
