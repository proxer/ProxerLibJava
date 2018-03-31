package me.proxer.library.api.ucp;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.ucp.Bookmark;
import me.proxer.library.enums.Category;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for retrieving the bookmarks of the user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class BookmarksEndpoint implements PagingLimitEndpoint<List<Bookmark>> {

    private final InternalApi internalApi;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override}))
    private Integer limit;

    /**
     * Sets the type of category to return.
     */
    @Nullable
    @Setter
    private Category category;

    /**
     * Sets if only available or only not available bookmarks should be returned.
     * <p>
     * This field being null means that all bookmarks are returned.
     */
    @Nullable
    @Setter
    private Boolean filterAvailable;

    BookmarksEndpoint(final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<List<Bookmark>> build() {
        return internalApi.bookmarks(category, page, limit, filterAvailable);
    }
}
