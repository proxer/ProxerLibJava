package com.proxerme.library.interfaces;

/**
 * An interface for all entities with a time.
 *
 * @author Ruben Gees
 */
public interface TimeItem {

    /**
     * Returns the time of the inheriting entity as a unix timestamp.
     *
     * @return The time.
     */
    long getTime();

}
