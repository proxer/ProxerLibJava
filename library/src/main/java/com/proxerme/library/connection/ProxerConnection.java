package com.proxerme.library.connection;

import android.support.annotation.NonNull;

import com.afollestad.bridge.Bridge;
import com.proxerme.library.info.ProxerTag;

/**
 * Singleton for accessing and configuring the API. Before using the API you must provide an API key
 * to the {@link #init(String)} method.
 *
 * @author Ruben Gees
 */
public class ProxerConnection {

    private static String key;

    /**
     * Sets the API key to be used. You must call this method before using the API. The onCreate
     * method in a Application subclass might be a good place.
     *
     * @param apiKey The API key.
     */
    public static void init(@NonNull String apiKey) {
        key = apiKey;
    }

    /**
     * Cancels all started requests of the specified tag.
     *
     * @param tag The {@link ProxerTag} to cancel.
     * @see ProxerTag
     */
    public static void cancel(@ProxerTag.ConnectionTag int tag) {
        Bridge.cancelAll().tag(String.valueOf(tag)).commit();
    }

    /**
     * Cleans up references and left open connections. You should call this method somewhere in the
     * lifecycle, but don't have to. A good place might be the onDestroy method of your main
     * Activity.
     */
    public static void cleanup() {
        key = null;

        Bridge.destroy();
    }

    /**
     * Returns the API key passed through the {@link #init(String)} method. An Exception is thrown
     * if no API key has been passed.
     *
     * @return The API key.
     */
    @NonNull
    public static String getKey() {
        if (key == null) {
            throw new RuntimeException("Please set your api key through the init method.");
        }

        return key;
    }
}