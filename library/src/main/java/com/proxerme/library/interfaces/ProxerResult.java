package com.proxerme.library.interfaces;

/**
 * The base for all Results, returned by a {@link com.proxerme.library.connection.ProxerRequest}.
 *
 * @param <T> The type of the entity in the inheriting Result.
 * @author Ruben Gees
 */
public interface ProxerResult<T> {

    /**
     * Returns the item of this Result. This might be null in some cases.
     *
     * @return The item.
     */
    T getItem();
}
