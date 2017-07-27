package me.proxer.library.entitiy;

import javax.annotation.Nonnull;

/**
 * An interface for all entities with an id.
 *
 * @author Ruben Gees
 */
public interface ProxerIdItem {

    /**
     * Returns the id.
     */
    @Nonnull
    String getId();
}
