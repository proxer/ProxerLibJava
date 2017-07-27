package me.proxer.library.api;

/**
 * Interface for classes which implement both {@link PagingEndpoint} and {@link LimitEndpoint}.
 *
 * @author Ruben Gees
 */
public interface PagingLimitEndpoint<T> extends PagingEndpoint<T>, LimitEndpoint<T> {
}
