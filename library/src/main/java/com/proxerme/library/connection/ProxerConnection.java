package com.proxerme.library.connection;

import android.support.annotation.NonNull;

import com.afollestad.bridge.Bridge;
import com.proxerme.library.info.ProxerTag;

/**
 * Main class to start all requests and manage the networking library.
 *
 * @author Ruben Gees
 */
public class ProxerConnection {

    private static String key;

    public static void init(@NonNull String apiKey) {
        key = apiKey;
    }

    /**
     * Cancels all started requests of the specified tag.
     *
     * @param tag The {@link ProxerTag} to cancel
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
        Bridge.destroy();
    }

    @NonNull
    public static String getKey() {
        if (key == null) {
            throw new RuntimeException("Please set your api key through the init method.");
        }

        return key;
    }
}