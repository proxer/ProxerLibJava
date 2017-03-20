package me.proxer.library.entitiy;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * An interface for all entities with a time.
 *
 * @author Ruben Gees
 */
public interface ProxerDateItem {

    /**
     * Returns the time.
     */
    @NotNull
    Date getDate();
}
