package com.proxerme.library.connection;

import com.afollestad.bridge.Bridge;
import com.proxerme.library.info.ProxerTag;

/**
 * Main class to start all requests and manage the networking library.
 *
 * @author Ruben Gees
 */
public class ProxerConnection {

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
}