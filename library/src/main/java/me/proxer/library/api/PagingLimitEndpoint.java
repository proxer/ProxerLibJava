package me.proxer.library.api;

import javax.annotation.Nullable;

/**
 * Interface for classes which implement both {@link PagingEndpoint} and {@link LimitEndpoint}.
 *
 * @author Ruben Gees
 */
public interface PagingLimitEndpoint<T> extends PagingEndpoint<T>, LimitEndpoint<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    PagingLimitEndpoint<T> page(@Nullable Integer page);

    /**
     * {@inheritDoc}
     */
    @Override
    PagingLimitEndpoint<T> limit(@Nullable Integer limit);
}
