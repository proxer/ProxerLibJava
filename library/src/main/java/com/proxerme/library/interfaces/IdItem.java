package com.proxerme.library.interfaces;

import org.jetbrains.annotations.NotNull;

/**
 * An interface for all entities with an id.
 *
 * @author Ruben Gees
 */
public interface IdItem {

    /**
     * Returns the id.
     */
    @NotNull
    String getId();
}
