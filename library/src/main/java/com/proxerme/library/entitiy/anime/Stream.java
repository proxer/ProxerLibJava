package com.proxerme.library.entitiy.anime;

import com.proxerme.library.entitiy.interfaces.IdItem;
import com.proxerme.library.entitiy.interfaces.ImageItem;
import com.proxerme.library.entitiy.interfaces.TimeItem;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * Entity representing a single streaming option of an anime.
 *
 * @author Ruben Gees
 */
@Value
public class Stream implements IdItem, ImageItem, TimeItem {

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "id")
    private String id;

    /**
     * The type of hoster of this stream.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "type")
    private String hoster;

    /**
     * The name of the hoster.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "name")
    private String hosterName;

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "img")
    private String imageId;

    /**
     * The id of the uploader.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "uploader")
    private String uploaderId;

    /**
     * The username of the uploader.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "username")
    private String uploaderName;

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "timestamp")
    private Date time;

    /**
     * The id of the translator group, if present.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "tid")
    private String translatorGroupId;

    /**
     * The name of the translator group, if present.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "tname")
    private String translatorGroupName;
}
