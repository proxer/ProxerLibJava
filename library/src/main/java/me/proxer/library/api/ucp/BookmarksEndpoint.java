package me.proxer.library.api.ucp;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.ucp.Bookmark;
import me.proxer.library.enums.Category;

import javax.annotation.Nonnull;
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
    @Setter(onMethod = @__({@Nonnull}))
    private Category category;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @Nonnull}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @Nonnull}))
    private Integer limit;

    BookmarksEndpoint(@Nonnull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public ProxerCall<List<Bookmark>> build() {
        return internalApi.bookmarks(category, page, limit);
    }
}
