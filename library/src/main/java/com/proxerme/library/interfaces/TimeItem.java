package com.proxerme.library.interfaces;

import java.util.Date;

/**
 * An interface for all entities with a time.
 *
 * @author Ruben Gees
 */
public interface TimeItem {

    /**
     * Returns the time of the inheriting entity.
     *
     * @return The time.
     */
    Date getTime();
}
