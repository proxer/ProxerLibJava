package com.proxerme.library;

/**
 * A callback for a successful request.
 *
 * @param <T> The type of the Result.
 */
public interface ProxerCallback<T> {

    /**
     * Called upon success for each Request with the result.
     *
     * @param result The result specified through the type parameter.
     */
    void onSuccess(T result);
}
