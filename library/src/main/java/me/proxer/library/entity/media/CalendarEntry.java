package me.proxer.library.entity.media;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.api.DelimitedStringSet;
import me.proxer.library.entity.ProxerDateItem;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.enums.CalendarDay;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.Set;

/**
 * Entity holding the data of a single calendar entry.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class CalendarEntry implements ProxerIdItem, ProxerDateItem {

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the id of the associated {@link me.proxer.library.entity.info.Entry}.
     */
    @Json(name = "eid")
    private String entryId;

    /**
     * Returns the name.
     */
    @Json(name = "entryname")
    private String name;

    /**
     * Returns the next episode to be aired.
     */
    @Json(name = "episode")
    private int episode;

    /**
     * Returns the title of the next episode to be aired. May be empty.
     */
    @Json(name = "episodeTitle")
    private String episodeTitle;

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "time")
    private Date date;

    /**
     * Returns the timezone of the country, the episode is aired in.
     */
    @Json(name = "timezone")
    private String timezone;

    /**
     * Returns the id of the television channel, transmitting the episode. "0" if not set.
     */
    @Json(name = "iid")
    private String industryId;

    /**
     * Returns the name of the television channel, transmitting the episode. May be null.
     */
    @Nullable
    @Json(name = "industryname")
    private String industryName;

    /**
     * Returns the day of the week, the episode is aired.
     */
    @Json(name = "weekday")
    private CalendarDay weekDay;

    /**
     * Returns the date (and time), the episode will be uploaded.
     * This is just an estimated value and can be imprecise.
     */
    @Json(name = "uptime")
    private Date uploadDate;

    /**
     * Returns the genres.
     */
    @DelimitedStringSet(valuesToKeep = "Slice of Life")
    @Json(name = "genre")
    private Set<String> genres;

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
