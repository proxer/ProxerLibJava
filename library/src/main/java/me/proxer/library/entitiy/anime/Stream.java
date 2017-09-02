package me.proxer.library.entitiy.anime;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerDateItem;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.entitiy.ProxerImageItem;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * Entity representing a single streaming option of an anime.
 *
 * @author Ruben Gees
 */
@Value
public class Stream implements ProxerIdItem, ProxerImageItem, ProxerDateItem {

    /**
     * {@inheritDoc}
     */
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
    @Json(name = "timestamp")
    private Date date;

    /**
     * Returns the id of the translator group, if present.
     */
    @Getter(onMethod = @__({@Nullable}))
    @Json(name = "tid")
    private String translatorGroupId;

    /**
     * Returns the name of the translator group, if present.
     */
    @Getter(onMethod = @__({@Nullable}))
    @Json(name = "tname")
    private String translatorGroupName;
}
