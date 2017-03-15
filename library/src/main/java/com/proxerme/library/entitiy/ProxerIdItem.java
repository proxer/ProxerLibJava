package com.proxerme.library.entitiy;

import org.jetbrains.annotations.NotNull;

/**
 * An interface for all entities with an id.
 *
 * @author Ruben Gees
 */
public interface ProxerIdItem {

    /**
     * Returns the id.
     */
    @NotNull
    String getId();
}
