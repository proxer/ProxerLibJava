package com.proxerme.library.api.ucp;

import com.proxerme.library.api.LimitEndpoint;
import com.proxerme.library.api.PagingEndpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.ucp.Bookmark;
import com.proxerme.library.enums.Category;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Endpoint for retrieving the bookmarks of the user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class BookmarksEndpoint implements PagingEndpoint, LimitEndpoint {

    private final InternalApi internalApi;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private Category category;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @NotNull}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @NotNull}))
    private Integer limit;

    BookmarksEndpoint(@NotNull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<List<Bookmark>> build() {
        return internalApi.bookmarks(category, page, limit);
    }
}
