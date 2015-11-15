package com.proxerme.library.interfaces;

import android.support.annotation.NonNull;

/**
 * An interface for all entities with an id.
 *
 * @author Ruben Gees
 */
public interface IdItem {

    /**
     * Returns the id of the inheriting entity.
     *
     * @return The id.
     */
    @NonNull
    String getId();

}
