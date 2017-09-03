package me.proxer.library.entity.manga;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerDateItem;
import me.proxer.library.entity.ProxerIdItem;

import javax.annotation.Nullable;
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
    @Getter(onMethod = @__({@Override}))
    @Json(name = "cid")
    private String id;

    /**
     * Returns the id of the associated entry.
     */
    @Json(name = "eid")
    private String entryId;

    /**
     * Returns the title.
     */
    @Json(name = "title")
    private String title;

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
     * Returns the id of the scan group if present.
     */
    @Nullable
    @Getter
    @Json(name = "tid")
    private String scanGroupId;

    /**
     * Returns the name of the scan group if present.
     */
    @Nullable
    @Getter
    @Json(name = "tname")
    private String scanGroupName;

    /**
     * Returns the server this page is saved on. To be used for retrieving the url.
     */
    @Json(name = "server")
    private String server;

    /**
     * Returns the actual pages of the chapter..
     */
    @Json(name = "pages")
    private List<Page> pages;

    @SuppressWarnings("checkstyle:parameternumber")
    public Chapter(final String id, final String entryId, final String title, final String uploaderId,
                   final String uploaderName, final Date date, @Nullable final String scanGroupId,
                   @Nullable final String scanGroupName, final String server, final List<Page> pages) {
        this.id = id;
        this.entryId = entryId;
        this.title = title;
        this.uploaderId = uploaderId;
        this.uploaderName = uploaderName;
        this.date = date;
        this.scanGroupId = scanGroupId;
        this.scanGroupName = scanGroupName;
        this.server = server;
        this.pages = pages;
    }
}
