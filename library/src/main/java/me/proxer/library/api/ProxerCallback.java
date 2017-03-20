package me.proxer.library.api;

/**
 * A callback for a successful request.
 *
 * @param <T> The type of the Result.
 * @author Ruben Gees
 */
public interface ProxerCallback<T> {

    /**
     * Called upon success for each Request with the {@code result}.
     */
    void onSuccess(T result);
}
