package me.proxer.library.entity.anime;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerDateItem;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.entity.ProxerImageItem;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * Entity representing a single streaming option of an anime.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class Stream implements ProxerIdItem, ProxerImageItem, ProxerDateItem {

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the type of hoster of this stream.
     */
    @Json(name = "type")
    private String hoster;

    /**
     * Returns the name of the hoster.
     */
    @Json(name = "name")
    private String hosterName;

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "img")
    private String image;

    /**
     * Returns the id of the uploader.
     */
    @Json(name = "uploader")
    private String uploaderId;

    /**
     * Returns the username of the uploader.
     */
    @Json(name = "username")
    private String uploaderName;

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "timestamp")
    private Date date;

    /**
     * Returns the id of the translator group, if present.
     */
    @Nullable
    @Getter
    @Json(name = "tid")
    private String translatorGroupId;

    /**
     * Returns the name of the translator group, if present.
     */
    @Nullable
    @Getter
    @Json(name = "tname")
    private String translatorGroupName;

    /**
     * Returns if the stream is hosted at an official hoster like Crunchyroll.
     */
    @Getter
    @Json(name = "legal")
    public boolean isOfficial;
}
