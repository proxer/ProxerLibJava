package com.proxerme.library.interfaces;

import org.jetbrains.annotations.NotNull;

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
    @NotNull
    Date getTime();
}
