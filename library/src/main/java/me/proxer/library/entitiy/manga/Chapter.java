package me.proxer.library.entitiy.manga;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerDateItem;
import me.proxer.library.entitiy.ProxerIdItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.List;

/**
 * Entity representing a chapter in a single language of a manga.
 *
 * @author Ruben Gees
 */
@Value
public class Chapter implements ProxerIdItem, ProxerDateItem {

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "cid")
    private String id;

    /**
     * Returns the id of the associated entry.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "eid")
    private String entryId;

    /**
     * Returns the title.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "title")
    private String title;

    /**
     * Returns the id of the uploader.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "uploader")
    private String uploaderId;

    /**
     * Returns the username of the uploader.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "username")
    private String uploaderName;

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "timestamp")
    private Date date;

    /**
     * Returns the id of the scan group if present.
     */
    @Getter(onMethod = @__({@Nullable}))
    @Json(name = "tid")
    private String scanGroupId;

    /**
     * Returns the name of the scan group if present.
     */
    @Getter(onMethod = @__({@Nullable}))
    @Json(name = "tname")
    private String scanGroupName;

    /**
     * Returns the server this page is saved on. To be used for retriving the url.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "server")
    private String server;

    /**
     * Returns the actual pages of the chapter..
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "pages")
    private List<Page> pages;
}
