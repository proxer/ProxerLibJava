package me.proxer.library.entitiy;

import javax.annotation.Nonnull;
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
    @Nonnull
    Date getDate();
}
